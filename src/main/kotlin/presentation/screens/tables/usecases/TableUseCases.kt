package presentation.screens.tables.usecases

import data.repository.enterprises.excel.ExcelEnterpriseRepositoryImpl
import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.repository.materials.excel.ExcelMaterialRepositoryImpl
import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.pollutions.excel.ExcelPollutionRepositoryImpl
import data.repository.pollutions.remote.PollutionMySQLRepository
import data.repository.yearConcentrations.excel.ExcelYearConcentrationRepositoryImpl
import data.repository.yearConcentrations.remote.YearConcentrationMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.excel.enterprises.EnterpriseExcelStorageImpl
import data.storage.excel.materials.MaterialsExcelStorageImpl
import data.storage.excel.pollutions.PollutionsExcelStorageImpl
import data.storage.excel.yearConcentrations.ExcelYearConcentrationStorageImpl
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.PollutionsMySQLStorage
import data.storage.remote.yearConcentrations.YearConcentrationsMySQLStorage
import domain.useCases.get.GetEnterprisesFromRemoteRepositoryUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase
import domain.useCases.get.GetPollutionsFromRemoteRepositoryUseCase
import domain.useCases.get.GetYearConcentrationsFromRemoteRepositoryUseCase
import domain.useCases.loadFromExcel.LoadEnterprisesFromExcelUseCase
import domain.useCases.loadFromExcel.LoadMaterialsFromExcelUseCase
import domain.useCases.loadFromExcel.LoadPollutionsFromExcelUseCase
import domain.useCases.loadFromExcel.LoadYearConcentrationsFromExcelUseCase

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
    val loadYearConcentrationsFromExcelUseCase: LoadYearConcentrationsFromExcelUseCase
    = LoadYearConcentrationsFromExcelUseCase(
        remoteRepository = YearConcentrationMySQLRepository(
            storage = YearConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        excelRepository = ExcelYearConcentrationRepositoryImpl(
            storage = ExcelYearConcentrationStorageImpl()
        )
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
    val getYearConcentrationsFromRemoteRepositoryUseCase: GetYearConcentrationsFromRemoteRepositoryUseCase
    = GetYearConcentrationsFromRemoteRepositoryUseCase(
        repository = YearConcentrationMySQLRepository(
            storage = YearConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)