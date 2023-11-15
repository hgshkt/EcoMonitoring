package data.storage.excel.materials

import data.storage.excel.materials.model.ExcelMaterial

interface MaterialsExcelStorage {
    fun load(): MutableList<ExcelMaterial>
}