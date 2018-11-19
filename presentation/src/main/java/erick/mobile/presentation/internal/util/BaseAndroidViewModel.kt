package erick.mobile.presentation.internal.util

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

@Suppress("ConvertSecondaryConstructorToPrimary")
@SuppressLint("StaticFieldLeak")
abstract class BaseAndroidViewModel : AndroidViewModel {

    protected val context: Context

    constructor(application: Application) : super(application) {
        this.context = application
    }

    private val disposables = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        disposables += disposable
    }

    override fun onCleared() {
        disposables.dispose()
    }
}