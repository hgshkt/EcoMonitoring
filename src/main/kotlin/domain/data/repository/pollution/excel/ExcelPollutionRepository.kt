package domain.data.repository.pollution.excel

import domain.model.data.excel.ExcelPollutionData

interface ExcelPollutionRepository {
    fun getData(fileName: String): ExcelPollutionData
}