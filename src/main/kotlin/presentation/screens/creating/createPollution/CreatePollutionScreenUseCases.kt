package presentation.screens.creating.createPollution

import data.obtained.RiskCalculatorImpl
import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.useCases.create.CreatePollutionUseCase
import domain.useCases.get.GetEnterprisesFromRemoteRepositoryUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase

data class CreatePollutionScreenUseCases(
    val getEnterprisesFromRemoteRepositoryUseCase: GetEnterprisesFromRemoteRepositoryUseCase
    = GetEnterprisesFromRemoteRepositoryUseCase(
        repository = EnterpriseMySQLRepository(
            storage = EnterprisesMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val getMaterialsFromRemoteRepositoryUseCase: GetMaterialsFromRemoteRepositoryUseCase
    = GetMaterialsFromRemoteRepositoryUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val createUseCase: CreatePollutionUseCase = CreatePollutionUseCase(
        repository = PollutionMySQLRepository(
            storage = PollutionsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        riskCalculator = RiskCalculatorImpl(),
        materialsRemoteRepository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)
