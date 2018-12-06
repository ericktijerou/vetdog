package erick.mobile.presentation.internal.injection.module

import dagger.Module
import dagger.Provides
import erick.mobile.domain.Schedulers
import erick.mobile.domain.gateway.InventoryGateway
import erick.mobile.domain.interactor.DogFindBySizeUseCase
import erick.mobile.domain.interactor.DogGetByIdUseCase
import javax.inject.Singleton

@Module
internal class DomainModule {
    @Provides
    @Singleton
    internal fun provideDogGetBySizeUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): DogFindBySizeUseCase {
        return DogFindBySizeUseCase(schedulers, inventoryGateway)
    }

    @Provides
    @Singleton
    internal fun provideDogGetByIdUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): DogGetByIdUseCase {
        return DogGetByIdUseCase(schedulers, inventoryGateway)
    }
}
