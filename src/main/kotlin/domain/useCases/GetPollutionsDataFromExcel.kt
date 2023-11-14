package domain.useCases

import domain.data.pollution.excel.ExcelPollutionsLoader
import domain.model.data.excel.ExcelPollutionsData

fun getPollutionsDataFromExcel(
    loader: ExcelPollutionsLoader
): ExcelPollutionsData {
    return loader.getData()
}