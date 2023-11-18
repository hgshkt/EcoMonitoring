package data.repository.pollutions.excel

import data.mappers.excelDataToDomain.MapperExcelToDomainPollution
import data.mappers.excelDataToDomain.toDomain
import data.storage.excel.pollutions.PollutionsExcelStorage
import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.model.data.excel.ExcelPollutionData

class ExcelPollutionRepositoryImpl(
    private val storage: PollutionsExcelStorage,
    private val mapper: MapperExcelToDomainPollution
): ExcelPollutionRepository {
    override fun getData(fileName: String): ExcelPollutionData {
        val pollutions = storage.load(fileName).map {
            it.toDomain(mapper)
        }
        return ExcelPollutionData(pollutions = pollutions.toMutableList())
    }
}