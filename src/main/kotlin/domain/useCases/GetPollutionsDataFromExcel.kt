package domain.useCases

import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.model.data.excel.ExcelPollutionData

class GetPollutionsDataFromExcel(
    private val repository: ExcelPollutionRepository
) {
    fun getPollutionsDataFromExcel(): ExcelPollutionData {
        return repository.getData()
    }
}