package presentation.screens.creating.createTaxRate

import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.taxRate.remote.TaxRateRemoteRepositoryImpl
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.taxRate.TaxRateMySQLStorage
import domain.useCases.create.CreateTaxRateUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase

data class CreateTaxRateScreenUseCases(
    val getMaterials: GetMaterialsFromRemoteRepositoryUseCase
    = GetMaterialsFromRemoteRepositoryUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val create: CreateTaxRateUseCase = CreateTaxRateUseCase(
        repository = TaxRateRemoteRepositoryImpl(
            storage = TaxRateMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)