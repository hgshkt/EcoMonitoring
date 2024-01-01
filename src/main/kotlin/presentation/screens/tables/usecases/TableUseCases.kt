package presentation.screens.tables.usecases

import data.obtained.RiskCalculatorImpl
import data.repository.enterprises.excel.ExcelEnterpriseRepositoryImpl
import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.repository.materials.excel.ExcelMaterialRepositoryImpl
import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.pollutions.excel.ExcelPollutionRepositoryImpl
import data.repository.pollutions.remote.PollutionMySQLRepository
import data.repository.yearConcentrations.remote.YearConcentrationRemoteRepository
import data.storage.DatabaseConnectionData
import data.storage.excel.enterprises.EnterpriseExcelStorageImpl
import data.storage.excel.materials.MaterialsExcelStorageImpl
import data.storage.excel.pollutions.PollutionsExcelStorageImpl
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.PollutionsMySQLStorage
import data.storage.remote.yearConcentration.YearConcentrationMySQLStorage
import domain.useCases.get.GetEnterprisesFromRemoteRepositoryUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase
import domain.useCases.get.GetPollutionsFromRemoteRepositoryUseCase
import domain.useCases.get.GetYearConcentrationsFromRemoteRepositoryUseCase
import domain.useCases.loadFromExcel.LoadEnterprisesFromExcelUseCase
import domain.useCases.loadFromExcel.LoadMaterialsFromExcelUseCase
import domain.useCases.loadFromExcel.LoadPollutionsFromExcelUseCase

data class TableUseCases(
    val loadEnterprisesFromExcelUseCase: LoadEnterprisesFromExcelUseCase = LoadEnterprisesFromExcelUseCase(
        remoteRepository = EnterpriseMySQLRepository(
            storage = EnterprisesMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        excelRepository = ExcelEnterpriseRepositoryImpl(
            storage = EnterpriseExcelStorageImpl()
        )
    ),
    val loadMaterialsFromExcelUseCase: LoadMaterialsFromExcelUseCase = LoadMaterialsFromExcelUseCase(
        remoteRepository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        excelRepository = ExcelMaterialRepositoryImpl(
            storage = MaterialsExcelStorageImpl()
        )
    ),
    val loadPollutionsFromExcelUseCase: LoadPollutionsFromExcelUseCase = LoadPollutionsFromExcelUseCase(
        remoteRepository = PollutionMySQLRepository(
            storage = PollutionsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        excelRepository = ExcelPollutionRepositoryImpl(
            storage = PollutionsExcelStorageImpl()
        ),
        remoteMaterialRepository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        riskCalculator = RiskCalculatorImpl()
    ),
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
    val getPollutionsFromRemoteRepositoryUseCase: GetPollutionsFromRemoteRepositoryUseCase
    = GetPollutionsFromRemoteRepositoryUseCase(
        repository = PollutionMySQLRepository(
            storage = PollutionsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val getYearConcentrationsFromRemoteRepositoryUseCase: GetYearConcentrationsFromRemoteRepositoryUseCase =
        GetYearConcentrationsFromRemoteRepositoryUseCase(
            repository = YearConcentrationRemoteRepository(
                storage = YearConcentrationMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )
)