package domain.data.repository.material.excel

import domain.model.data.excel.ExcelMaterialData

interface ExcelMaterialRepository {
    fun getData(): ExcelMaterialData
}