package data.repository.enterprises.excel

import data.mappers.excel.enterprise.toDomain
import data.storage.excel.enterprises.EnterpriseExcelStorage
import domain.data.repository.enterprise.excel.ExcelEnterpriseRepository
import domain.model.data.excel.ExcelEnterpriseData

class ExcelEnterpriseRepositoryImpl(
    private val storage: EnterpriseExcelStorage
): ExcelEnterpriseRepository {
    override fun getData(fileName: String): ExcelEnterpriseData {
        val enterprises = storage.load(fileName).map {
            it.toDomain()
        }
        return ExcelEnterpriseData(enterprises = enterprises.toMutableList())
    }
}