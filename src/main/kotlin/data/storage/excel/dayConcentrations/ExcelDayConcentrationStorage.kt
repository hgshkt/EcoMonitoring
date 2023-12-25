package data.storage.excel.dayConcentrations

import data.storage.excel.dayConcentrations.excel.ExcelDayConcentration

interface ExcelDayConcentrationStorage {
    fun load(fileName: String): MutableList<ExcelDayConcentration>
}