package erick.mobile.presentation.internal.injection.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import erick.mobile.presentation.internal.injection.DaggerApplication
import erick.mobile.presentation.internal.injection.module.ActivitiesModule
import erick.mobile.presentation.internal.injection.module.AppModule
import erick.mobile.presentation.internal.injection.module.DataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivitiesModule::class,
    AppModule::class,
    DataModule::class])
internal interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()
}