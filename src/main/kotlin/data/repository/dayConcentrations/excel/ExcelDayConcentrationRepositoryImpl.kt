package data.repository.dayConcentrations.excel

import data.mappers.excel.dayConcentration.toDomain
import data.storage.excel.dayConcentrations.ExcelDayConcentrationStorage
import domain.data.repository.dayConcentration.excel.ExcelDayConcentrationRepository
import domain.model.data.excel.ExcelDayConcentrationData

class ExcelDayConcentrationRepositoryImpl(
    private val storage: ExcelDayConcentrationStorage
) : ExcelDayConcentrationRepository {
    override fun getData(fileName: String): ExcelDayConcentrationData {
        val concentrations = storage.load(fileName).map { it.toDomain() }
        return ExcelDayConcentrationData(concentrations = concentrations.toMutableList())
    }
}