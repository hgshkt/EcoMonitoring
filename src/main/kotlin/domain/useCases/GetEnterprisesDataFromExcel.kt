package domain.useCases

import domain.data.excel.loader.ExcelEnterprisesLoader
import domain.model.excelData.ExcelEnterprisesData

fun getEnterprisesDataFromExcel(
    loader: ExcelEnterprisesLoader
): ExcelEnterprisesData {
    return loader.getData()
}