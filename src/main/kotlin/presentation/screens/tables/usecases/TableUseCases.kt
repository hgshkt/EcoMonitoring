package presentation.screens.tables.usecases

import data.obtained.RiskCalculatorImpl
import data.repository.enterprises.excel.ExcelEnterpriseRepositoryImpl
import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.repository.materials.excel.ExcelMaterialRepositoryImpl
import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.pollutions.excel.ExcelPollutionRepositoryImpl
import data.repository.pollutions.remote.PollutionMySQLRepository
import data.repository.dayConcentrations.excel.ExcelDayConcentrationRepositoryImpl
import data.repository.dayConcentrations.remote.DayConcentrationMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.excel.enterprises.EnterpriseExcelStorageImpl
import data.storage.excel.materials.MaterialsExcelStorageImpl
import data.storage.excel.pollutions.PollutionsExcelStorageImpl
import data.storage.excel.dayConcentrations.ExcelDayConcentrationStorageImpl
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.PollutionsMySQLStorage
import data.storage.remote.dayConcentrations.DayConcentrationsMySQLStorage
import domain.useCases.get.GetEnterprisesFromRemoteRepositoryUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase
import domain.useCases.get.GetPollutionsFromRemoteRepositoryUseCase
import domain.useCases.get.GetDayConcentrationsFromRemoteRepositoryUseCase
import domain.useCases.loadFromExcel.LoadEnterprisesFromExcelUseCase
import domain.useCases.loadFromExcel.LoadMaterialsFromExcelUseCase
import domain.useCases.loadFromExcel.LoadPollutionsFromExcelUseCase
import domain.useCases.loadFromExcel.LoadDayConcentrationsFromExcelUseCase

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
        )
    ),
    val loadDayConcentrationsFromExcelUseCase: LoadDayConcentrationsFromExcelUseCase
    = LoadDayConcentrationsFromExcelUseCase(
        remoteRepository = DayConcentrationMySQLRepository(
            storage = DayConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        excelRepository = ExcelDayConcentrationRepositoryImpl(
            storage = ExcelDayConcentrationStorageImpl()
        ),
        materialsRemoteRepository = MaterialMySQLRepository(
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
    val getDayConcentrationsFromRemoteRepositoryUseCase: GetDayConcentrationsFromRemoteRepositoryUseCase
    = GetDayConcentrationsFromRemoteRepositoryUseCase(
        repository = DayConcentrationMySQLRepository(
            storage = DayConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)