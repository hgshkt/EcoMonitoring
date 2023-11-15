package data.storage.excel.pollutions

import data.storage.excel.pollutions.model.ExcelPollution

interface PollutionsExcelStorage {
    fun load(): MutableList<ExcelPollution>
}