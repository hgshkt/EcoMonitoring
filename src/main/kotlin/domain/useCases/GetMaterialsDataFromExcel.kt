package domain.useCases

import domain.data.excel.loader.ExcelMaterialsLoader
import domain.model.excelData.ExcelMaterialsData

fun getMaterialsDataFromExcel(
    loader: ExcelMaterialsLoader
): ExcelMaterialsData {
    return loader.getData()
}