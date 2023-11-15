package data.storage.excel.materials

import data.storage.excel.materials.model.ExcelStorageMaterial
import domain.model.Material

interface MaterialsExcelStorage {
    fun load(fileName: String): MutableList<ExcelStorageMaterial>
}