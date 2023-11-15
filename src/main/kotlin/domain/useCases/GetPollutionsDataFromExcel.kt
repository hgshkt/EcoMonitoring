package domain.useCases

import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.model.data.excel.ExcelPollutionData

fun getPollutionsDataFromExcel(
    loader: ExcelPollutionRepository
): ExcelPollutionData {
    return loader.getData()
}