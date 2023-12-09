package data.repository.yearConcentrations.excel

import data.mappers.excel.yearConcentration.toDomain
import data.storage.excel.yearConcentrations.ExcelYearConcentrationStorage
import domain.data.repository.yearConcentration.excel.ExcelYearConcentrationRepository
import domain.model.data.excel.ExcelYearConcentrationData

class ExcelYearConcentrationRepositoryImpl(
    private val storage: ExcelYearConcentrationStorage
) : ExcelYearConcentrationRepository {
    override fun getData(fileName: String): ExcelYearConcentrationData {
        val concentrations = storage.load(fileName).map { it.toDomain() }
        return ExcelYearConcentrationData(concentrations = concentrations.toMutableList())
    }
}