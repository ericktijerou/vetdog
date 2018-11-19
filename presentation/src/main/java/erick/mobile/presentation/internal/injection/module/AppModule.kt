package erick.mobile.presentation.internal.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import erick.mobile.domain.Schedulers
import erick.mobile.presentation.internal.injection.DaggerApplication
import erick.mobile.presentation.internal.scheduler.AppSchedulers
import erick.mobile.presentation.navigation.Navigator
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    internal fun providesContext(application: DaggerApplication): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun provideSchedulers(): Schedulers = AppSchedulers()

    @Provides
    @Singleton
    internal fun provideNavigator() = Navigator()

}
