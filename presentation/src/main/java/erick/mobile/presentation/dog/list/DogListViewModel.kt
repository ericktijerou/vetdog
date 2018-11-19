package erick.mobile.presentation.dog.list

import android.app.Application
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import erick.mobile.domain.entity.Dog
import erick.mobile.domain.interactor.DogFindBySizeUseCase
import erick.mobile.presentation.R
import erick.mobile.presentation.dog.list.mapper.DogMapper
import erick.mobile.presentation.dog.list.model.DogModel
import erick.mobile.presentation.internal.util.BaseAndroidViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class DogListViewModel(context: Context, private val dogFindBySizeUseCase: DogFindBySizeUseCase)
    : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = DogMapper(context)

    val loading = ObservableBoolean()
    val result = ObservableArrayList<DogModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    private var type = "med"

    fun loadEventList(type: String, refresh: Boolean = false) {
        this.type = type
        addDisposable(findEventByType(type, refresh))
    }

    fun refresh() = loadEventList(type, true)

    private fun findEventByType(type: String, refresh: Boolean): Disposable {
        val params = Pair(type, refresh)
        return dogFindBySizeUseCase.execute(params)
            .subscribeWith(object : DisposableObserver<List<Dog>>() {

                override fun onStart() {
                    loading.set(true)
                    empty.set(false)
                }

                override fun onNext(t: List<Dog>) {
                    loading.set(false)
                    result.clear()
                    result.addAll(t.map { mapper.toModel(it) })
                    empty.set(t.isEmpty())
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