package data.repository.yearConcentrations.excel

import data.storage.excel.yearConcentration.YearConcentrationExcelStorage
import domain.data.repository.yearConcentration.excel.ExcelYearConcentrationRepository
import domain.model.YearConcentration

class ExcelYearConcentrationRepositoryImpl(
    private val storage: YearConcentrationExcelStorage
): ExcelYearConcentrationRepository {
    override fun getData(fileName: String): MutableList<YearConcentration> {
        return storage.load(fileName).map {
            it.toDomain()
        }
    }
}