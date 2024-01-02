package presentation.screens.calculator

import data.obtained.calculatorImpls.MassCalculatorImpl
import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import domain.useCases.calculation.CalculateMassUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase

data class CalculatorUseCases(

    val calcMass: CalculateMassUseCase = CalculateMassUseCase(
        calculator = MassCalculatorImpl()
    ),

    val getMaterials: GetMaterialsFromRemoteRepositoryUseCase
    = GetMaterialsFromRemoteRepositoryUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),

)
