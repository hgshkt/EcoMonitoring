package presentation.screens.tables.usecases

import data.obtained.DamageCalculatorImpl
import data.obtained.RiskCalculatorImpl
import data.obtained.calculatorImpls.DLAtmosphereTaxCalculatorImpl
import data.obtained.calculatorImpls.DamageDataCalculatorImpl
import data.obtained.calculatorImpls.MaterialCalculatorImpl
import data.obtained.calculatorImpls.TaxCalculatorImpl
import data.repository.damageData.excel.ExcelDamageDataRepositoryImpl
import data.repository.damageData.remote.DamageDataRemoteRepositoryImpl
import data.repository.enterprises.excel.ExcelEnterpriseRepositoryImpl
import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.repository.materials.excel.ExcelMaterialRepositoryImpl
import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.pollutions.excel.ExcelPollutionRepositoryImpl
import data.repository.pollutions.remote.PollutionMySQLRepository
import data.repository.taxRate.excel.ExcelTaxRateRepositoryImpl
import data.repository.taxRate.remote.TaxRateRemoteRepositoryImpl
import data.repository.yearConcentrations.excel.ExcelYearConcentrationRepositoryImpl
import data.repository.yearConcentrations.remote.YearConcentrationRemoteRepository
import data.storage.DatabaseConnectionData
import data.storage.excel.damageData.DamageDataExcelStorageImpl
import data.storage.excel.enterprises.EnterpriseExcelStorageImpl
import data.storage.excel.materials.MaterialsExcelStorageImpl
import data.storage.excel.pollutions.PollutionsExcelStorageImpl
import data.storage.excel.taxRate.ExcelTaxRateStorage
import data.storage.excel.taxRate.ExcelTaxRateStorageImpl
import data.storage.excel.yearConcentration.ExcelYearConcentrationStorageImpl
import data.storage.remote.damageData.DamageDataRemoteStorageImpl
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.PollutionsMySQLStorage
import data.storage.remote.taxRate.TaxRateMySQLStorage
import data.storage.remote.yearConcentration.YearConcentrationMySQLStorage
import domain.useCases.get.*
import domain.useCases.loadFromExcel.*

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
        riskCalculator = RiskCalculatorImpl(),
        damageCalculator = DamageCalculatorImpl(),
        damageDataRemoteRepository = DamageDataRemoteRepositoryImpl(
            storage = DamageDataRemoteStorageImpl(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val loadYearConcentrationsFromExcelUseCase: LoadYearConcentrationsFromExcelUseCase
    = LoadYearConcentrationsFromExcelUseCase(
        remoteRepository = YearConcentrationRemoteRepository(
            storage = YearConcentrationMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        excelRepository = ExcelYearConcentrationRepositoryImpl(
            storage = ExcelYearConcentrationStorageImpl()
        ),
        materialRemoteRepository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        calculator = MaterialCalculatorImpl()
    ),
    val loadDamageDataFromExcelUseCase: LoadDamageDataFromExcelUseCase
    = LoadDamageDataFromExcelUseCase(
        excelRepository = ExcelDamageDataRepositoryImpl(
            storage = DamageDataExcelStorageImpl()
        ),
        remoteRepository = DamageDataRemoteRepositoryImpl(
            storage = DamageDataRemoteStorageImpl(
                connectionData = DatabaseConnectionData()
            )
        ),
        calculator = DamageDataCalculatorImpl(),
        pollutionRepository = PollutionMySQLRepository(
            storage = PollutionsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        damageCalculator = DamageCalculatorImpl(),
        materialRepository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val loadTaxRatesFromExcelUseCase: LoadTaxRateFromExcelUseCase = LoadTaxRateFromExcelUseCase(
        taxRateExcelRepository = ExcelTaxRateRepositoryImpl(
            storage = ExcelTaxRateStorageImpl()
        ),
        taxRateRemoteRepository = TaxRateRemoteRepositoryImpl(
            storage = TaxRateMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        taxCalculator = TaxCalculatorImpl(
            atmosphereTaxCalculator = DLAtmosphereTaxCalculatorImpl(),
            taxRatesRepository = TaxRateMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        ),
        pollutionsRepository = PollutionMySQLRepository(
            storage = PollutionsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
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
    val getYearConcentrationsFromRemoteRepositoryUseCase: GetYearConcentrationsFromRemoteRepositoryUseCase =
        GetYearConcentrationsFromRemoteRepositoryUseCase(
            repository = YearConcentrationRemoteRepository(
                storage = YearConcentrationMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        ),
    val getDamageData: GetDamageDataFromRemoteRepositoryUseCase
    = GetDamageDataFromRemoteRepositoryUseCase(
        remoteRepository = DamageDataRemoteRepositoryImpl(
            storage = DamageDataRemoteStorageImpl(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val getTaxRates: GetTaxRatesFromRemoteRepository = GetTaxRatesFromRemoteRepository(
        repository = TaxRateRemoteRepositoryImpl(
            storage = TaxRateMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)