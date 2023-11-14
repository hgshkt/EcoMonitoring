package domain.useCases

import domain.data.material.excel.ExcelMaterialsLoader
import domain.model.data.excel.ExcelMaterialsData

fun getMaterialsDataFromExcel(
    loader: ExcelMaterialsLoader
): ExcelMaterialsData {
    return loader.getData()
}