package data.storage.excel.yearConcentration

import data.storage.excel.yearConcentration.model.ExcelYearConcentration

interface YearConcentrationExcelStorage {
    fun load(fileName: String): MutableList<ExcelYearConcentration>
}