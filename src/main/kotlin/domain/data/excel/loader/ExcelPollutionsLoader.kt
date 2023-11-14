package domain.data.excel.loader

import domain.model.excelData.ExcelPollutionsData

interface ExcelPollutionsLoader {
    fun getData(): ExcelPollutionsData
}