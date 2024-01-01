package domain.data.repository.yearConcentration.excel

import domain.model.YearConcentration

interface ExcelYearConcentrationRepository {
    fun getData(fileName: String): MutableList<YearConcentration>
}