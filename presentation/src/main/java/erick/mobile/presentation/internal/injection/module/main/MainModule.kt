package erick.mobile.presentation.internal.injection.module.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.Module
import dagger.Provides
import erick.mobile.domain.Schedulers
import erick.mobile.domain.gateway.InventoryGateway
import erick.mobile.domain.interactor.DogFindBySizeUseCase
import erick.mobile.domain.interactor.DogGetByIdUseCase
import erick.mobile.presentation.dog.list.DogListViewModel
import erick.mobile.presentation.internal.injection.scope.DogScope

@Module
internal abstract class MainModule {

//    @ContributesAndroidInjector
//    internal abstract fun contributeEventListFragment(): EventListFragment

    @Module
    companion object {

        @DogScope
        @Provides
        @JvmStatic
        internal fun provideDogFindBySizeUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): DogFindBySizeUseCase {
            return DogFindBySizeUseCase(schedulers, inventoryGateway)
        }

        @DogScope
        @Provides
        @JvmStatic
        internal fun provideDogGetByIdUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): DogGetByIdUseCase {
            return DogGetByIdUseCase(schedulers, inventoryGateway)
        }

        @DogScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(context: Context,
                                             dogFindByTypeUseCase: DogFindBySizeUseCase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(DogListViewModel::class.java) ->
                            DogListViewModel(context, dogFindByTypeUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }
}