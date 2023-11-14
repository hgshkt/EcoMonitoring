package domain.data.excel.loader

import domain.model.excelData.ExcelMaterialsData

interface ExcelMaterialsLoader {
    fun getData(): ExcelMaterialsData
}