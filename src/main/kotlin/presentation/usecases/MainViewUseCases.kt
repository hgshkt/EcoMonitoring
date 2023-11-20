package presentation.usecases

import data.mappers.domainDataToRemote.MapperDomainPollutionToRemote
import data.mappers.excelDataToDomain.MapperExcelToDomainPollution
import data.mappers.excelDataToRemoteImpl.MapperExcelDataToRemoteImpl
import data.mappers.remoteDataToDomain.MapperRemoteToDomainPollution
import data.repository.enterprises.excel.ExcelEnterpriseRepositoryImpl
import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.repository.materials.excel.ExcelMaterialRepositoryImpl
import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.pollutions.excel.ExcelPollutionRepositoryImpl
import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.excel.enterprises.EnterpriseExcelStorageImpl
import data.storage.excel.materials.MaterialsExcelStorageImpl
import data.storage.excel.pollutions.PollutionsExcelStorage
import data.storage.excel.pollutions.PollutionsExcelStorageImpl
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.useCases.LoadEnterprisesFromExcelUseCase
import domain.useCases.LoadMaterialsFromExcelUseCase
import domain.useCases.LoadPollutionsFromExcelUseCase

data class MainViewUseCases(
    val loadEnterprisesFromExcelUseCase: LoadEnterprisesFromExcelUseCase = LoadEnterprisesFromExcelUseCase(
        remoteRepository = EnterpriseMySQLRepository(
            storage = EnterprisesMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        excelRepository = ExcelEnterpriseRepositoryImpl(
            storage = EnterpriseExcelStorageImpl()
        ),
        mapper = MapperExcelDataToRemoteImpl()
    ),
    val loadMaterialsFromExcelUseCase: LoadMaterialsFromExcelUseCase = LoadMaterialsFromExcelUseCase(
        remoteRepository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        excelRepository = ExcelMaterialRepositoryImpl(
            storage = MaterialsExcelStorageImpl()
        ),
        mapper = MapperExcelDataToRemoteImpl()
    ),
    val loadPollutionsFromExcelUseCase: LoadPollutionsFromExcelUseCase = LoadPollutionsFromExcelUseCase(
        remoteRepository = PollutionMySQLRepository(
            storage = PollutionsMySQLStorage(
                connectionData = DatabaseConnectionData()
            ),
            mapperRemoteToDomain = MapperRemoteToDomainPollution(
                enterpriseStorage = EnterprisesMySQLStorage(
                    connectionData = DatabaseConnectionData()
                ),
                materialStorage = MaterialMySQLStorage(
                    connectionData = DatabaseConnectionData()
                ),
            ),
            mapperDomainToRemote = MapperDomainPollutionToRemote(
                materialStorage = MaterialMySQLStorage(
                    connectionData = DatabaseConnectionData()
                ),
                enterpriseStorage = EnterprisesMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        ),
        excelRepository = ExcelPollutionRepositoryImpl(
            storage = PollutionsExcelStorageImpl(),
            mapper = MapperExcelToDomainPollution(
                enterpriseStorage = EnterprisesMySQLStorage(
                    connectionData = DatabaseConnectionData()
                ),
                materialStorage = MaterialMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        ),
        mapper = MapperExcelDataToRemoteImpl()
    )
)