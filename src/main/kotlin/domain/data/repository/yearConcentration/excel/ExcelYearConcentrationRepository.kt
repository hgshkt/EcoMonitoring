package domain.data.repository.yearConcentration.excel

import domain.model.data.excel.ExcelYearConcentrationData

interface ExcelYearConcentrationRepository {
    fun getData(fileName: String): ExcelYearConcentrationData
}