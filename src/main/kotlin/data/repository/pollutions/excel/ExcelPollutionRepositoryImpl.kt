package data.repository.pollutions.excel

import data.storage.excel.enterprises.EnterpriseExcelStorage
import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.model.data.excel.ExcelPollutionData

class ExcelPollutionRepositoryImpl(
    val storage: EnterpriseExcelStorage
): ExcelPollutionRepository {
    override fun getData(): ExcelPollutionData {
        val pollutions = storage.load().map {
            it.toDomain()
        }
        return ExcelPollutionData(pollutions = pollutions)
    }
}