package domain.useCases

import domain.data.excel.loader.ExcelPollutionsLoader
import domain.model.excelData.ExcelPollutionsData

fun getPollutionsDataFromExcel(
    loader: ExcelPollutionsLoader
): ExcelPollutionsData {
    return loader.getData()
}