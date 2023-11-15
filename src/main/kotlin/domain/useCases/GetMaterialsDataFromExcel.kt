package domain.useCases

import domain.data.repository.material.excel.ExcelMaterialRepository
import domain.model.data.excel.ExcelMaterialData

fun getMaterialsDataFromExcel(
    loader: ExcelMaterialRepository
): ExcelMaterialData {
    return loader.getData()
}