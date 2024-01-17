package presentation.screens.tables.usecases

import data.repository.taxRate.remote.TaxRateRemoteRepositoryImpl
import data.storage.DatabaseConnectionData
import data.storage.remote.taxRate.TaxRateMySQLStorage
import domain.useCases.delete.DeleteAllTaxRateUseCases
import domain.useCases.delete.DeleteTaxRateByIdUseCase
import domain.useCases.get.GetTaxRatesFromRemoteRepository

data class TaxRatesTableViewUseCases(
    val getAll: GetTaxRatesFromRemoteRepository = GetTaxRatesFromRemoteRepository(
        repository = TaxRateRemoteRepositoryImpl(
            storage = TaxRateMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val delete: DeleteTaxRateByIdUseCase = DeleteTaxRateByIdUseCase(
        repository = TaxRateRemoteRepositoryImpl(
            storage = TaxRateMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val deleteAll: DeleteAllTaxRateUseCases = DeleteAllTaxRateUseCases(
        repository = TaxRateRemoteRepositoryImpl(
            storage = TaxRateMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)
