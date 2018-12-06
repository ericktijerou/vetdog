package erick.mobile.presentation.internal.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import erick.mobile.presentation.dog.detail.DogActivity
import erick.mobile.presentation.dog.list.MainActivity
import erick.mobile.presentation.internal.injection.module.dog.DogModule
import erick.mobile.presentation.internal.injection.module.main.MainModule
import erick.mobile.presentation.internal.injection.scope.DogScope

@Module
internal abstract class ActivitiesModule {

    @DogScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @DogScope
    @ContributesAndroidInjector(modules = [DogModule::class])
    internal abstract fun contributeDogActivity(): DogActivity
}