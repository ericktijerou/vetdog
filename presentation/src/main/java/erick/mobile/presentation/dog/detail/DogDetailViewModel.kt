package erick.mobile.presentation.dog.detail

import android.app.Application
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import erick.mobile.domain.entity.Dog
import erick.mobile.domain.interactor.DogGetByIdUseCase
import erick.mobile.presentation.R
import erick.mobile.presentation.dog.list.mapper.DogMapper
import erick.mobile.presentation.dog.list.model.DogModel
import erick.mobile.presentation.internal.util.BaseAndroidViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class DogDetailViewModel(context: Context,
                         private val eventGetByIdUseCase: DogGetByIdUseCase)
    : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = DogMapper(context)

    val loading = ObservableBoolean()
    val dog = ObservableField<DogModel>()
    val error = ObservableField<String>()

    fun loadEventDetail(id: String) = addDisposable(getEventById(id))

    private fun getEventById(id: String): Disposable {
        return eventGetByIdUseCase.execute(id)
            .subscribeWith(object : DisposableObserver<Dog>() {

                override fun onStart() {
                    loading.set(true)
                }

                override fun onNext(result: Dog) {
                    loading.set(false)
                    dog.set(mapper.toModel(result))

                    //addDisposable(getVenueById(result))
                }

                override fun onError(t: Throwable) {
                    loading.set(false)
                    error.set(t.localizedMessage ?: t.message ?: context.getString(R.string.unknown_error))
                }

                override fun onComplete() {
                    // no-op
                }
            })
    }
}
