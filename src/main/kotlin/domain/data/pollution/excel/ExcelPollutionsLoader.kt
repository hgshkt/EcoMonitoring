package domain.data.pollution.excel

import domain.model.data.excel.ExcelPollutionsData

interface ExcelPollutionsLoader {
    fun getData(): ExcelPollutionsData
}