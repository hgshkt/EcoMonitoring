package domain.useCases

import domain.data.enterprise.excel.ExcelEnterprisesLoader
import domain.model.data.excel.ExcelEnterprisesData

fun getEnterprisesDataFromExcel(
    loader: ExcelEnterprisesLoader
): ExcelEnterprisesData {
    return loader.getData()
}