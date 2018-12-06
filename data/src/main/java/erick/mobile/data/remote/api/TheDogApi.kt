package erick.mobile.data.remote.api

import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import erick.mobile.data.BuildConfig
import erick.mobile.data.remote.api.util.AuthenticatorInterceptor
import erick.mobile.data.remote.api.util.MoshiConverters
import erick.mobile.data.remote.api.util.RetryAfterInterceptor
import erick.mobile.data.remote.model.DogRemoteModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class TheDogApi(baseUrl: String) : TheDogService {

    companion object {
        private const val TIMEOUT = 10L
    }

    private val service: TheDogService

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthenticatorInterceptor())
            .addInterceptor(RetryAfterInterceptor())

        val client = httpClient.build()

        val moshi = Moshi.Builder()
            .add(Wrapped.ADAPTER_FACTORY)
            .add(MoshiConverters())
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(TheDogService::class.java)
    }

    override fun getDog(id: String): Observable<DogRemoteModel> = service.getDog(id)

    override fun getDogs(size: String, limit: Int): Observable<List<DogRemoteModel>> = service.getDogs(size, limit)
}