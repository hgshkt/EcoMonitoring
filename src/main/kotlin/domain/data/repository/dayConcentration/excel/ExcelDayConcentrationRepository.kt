package domain.data.repository.dayConcentration.excel

import domain.model.data.excel.ExcelDayConcentrationData

interface ExcelDayConcentrationRepository {
    fun getData(fileName: String): ExcelDayConcentrationData
}