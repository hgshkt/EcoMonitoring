package data.repository.enterprises.excel

import data.mappers.excelDataToDomain.toDomain
import data.storage.excel.enterprises.EnterpriseExcelStorage
import domain.data.repository.enterprise.excel.ExcelEnterpriseRepository
import domain.model.data.excel.ExcelEnterpriseData

class ExcelEnterpriseRepositoryImpl(
    private val storage: EnterpriseExcelStorage
): ExcelEnterpriseRepository {
    override fun getData(): ExcelEnterpriseData {
        val enterprises = storage.load().map {
            it.toDomain()
        }
        return ExcelEnterpriseData(enterprises = enterprises.toMutableList())
    }
}