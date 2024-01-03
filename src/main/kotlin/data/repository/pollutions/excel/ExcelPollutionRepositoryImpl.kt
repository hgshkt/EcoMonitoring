package data.repository.pollutions.excel

import data.mappers.excel.pollution.toDomain
import data.storage.excel.pollutions.PollutionsExcelStorage
import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.model.Pollution
import domain.model.data.excel.ExcelPollutionData

class ExcelPollutionRepositoryImpl(
    private val storage: PollutionsExcelStorage
): ExcelPollutionRepository {
    override fun getData(fileName: String): MutableList<Pollution> {
        val pollutions = storage.load(fileName).map {
            it.toDomain()
        }
        return pollutions.toMutableList()
    }
}