package erick.mobile.presentation

import android.content.Context
import android.support.multidex.MultiDex
import android.util.Log
import com.facebook.stetho.Stetho
import erick.mobile.presentation.internal.injection.DaggerApplication
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import java.io.IOException
import java.net.SocketException

class MainApplication : DaggerApplication() {

    companion object {
        private const val LOG_TAG = "Theater"
    }

    /**
     * Versions of the platform prior to Android 5.0 (API level 21) use the Dalvik runtime for executing app code.
     * By default, Dalvik limits apps to a single classes.dex bytecode file per APK.
     * In order to get around this limitation, we can add the multidex support library to project.
     */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        setupReactiveX()
    }

    private fun setupReactiveX() {
        // Error handling in RxJava
        // https://github.com/ReactiveX/RxJava/wiki/What%27s-different-in-2.0#error-handling
        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                Log.w(LOG_TAG, "Undeliverable exception received, not sure what to do", e.cause)
                return@setErrorHandler
            }
            if (e is IOException || e is SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (e is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (e is NullPointerException || e is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), e)
                return@setErrorHandler
            }
            if (e is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), e)
                return@setErrorHandler
            }
        }
    }
}
