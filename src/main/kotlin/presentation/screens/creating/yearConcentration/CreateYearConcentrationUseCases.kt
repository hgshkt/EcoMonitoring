package presentation.screens.creating.yearConcentration

import data.obtained.RiskCalculatorImpl
import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.yearConcentrations.remote.YearConcentrationMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.yearConcentrations.YearConcentrationsMySQLStorage
import domain.useCases.create.CreateYearConcentrationUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase

data class CreateYearConcentrationUseCases(
    val getMaterialsFromRemoteRepositoryUseCase: GetMaterialsFromRemoteRepositoryUseCase
    = GetMaterialsFromRemoteRepositoryUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val createUseCase: CreateYearConcentrationUseCase = CreateYearConcentrationUseCase(
        repository = YearConcentrationMySQLRepository(
            storage = YearConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        materialsRemoteRepository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        riskCalculator = RiskCalculatorImpl()
    )
)