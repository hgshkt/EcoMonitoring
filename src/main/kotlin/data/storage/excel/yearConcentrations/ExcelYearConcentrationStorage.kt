package data.storage.excel.yearConcentrations

import data.storage.excel.yearConcentrations.excel.ExcelYearConcentration

interface ExcelYearConcentrationStorage {
    fun load(fileName: String): MutableList<ExcelYearConcentration>
}