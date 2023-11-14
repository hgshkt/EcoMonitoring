package domain.data.material.excel

import domain.model.data.excel.ExcelMaterialsData

interface ExcelMaterialsLoader {
    fun getData(): ExcelMaterialsData
}