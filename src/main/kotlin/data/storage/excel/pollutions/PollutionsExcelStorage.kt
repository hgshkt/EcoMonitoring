package data.storage.excel.pollutions

import data.storage.excel.pollutions.model.ExcelStoragePollution

interface PollutionsExcelStorage {
    fun load(fileName: String): MutableList<ExcelStoragePollution>
}