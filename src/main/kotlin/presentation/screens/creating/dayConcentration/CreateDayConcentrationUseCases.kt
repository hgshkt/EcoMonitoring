package presentation.screens.creating.dayConcentration

import data.obtained.RiskCalculatorImpl
import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.dayConcentrations.remote.DayConcentrationMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.dayConcentrations.DayConcentrationsMySQLStorage
import domain.useCases.create.CreateDayConcentrationUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase

data class CreateDayConcentrationUseCases(
    val getMaterialsFromRemoteRepositoryUseCase: GetMaterialsFromRemoteRepositoryUseCase
    = GetMaterialsFromRemoteRepositoryUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val createUseCase: CreateDayConcentrationUseCase = CreateDayConcentrationUseCase(
        repository = DayConcentrationMySQLRepository(
            storage = DayConcentrationsMySQLStorage(
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