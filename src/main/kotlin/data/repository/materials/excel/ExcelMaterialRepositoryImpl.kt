package data.repository.materials.excel

import data.mappers.excelDataToDomain.toDomain
import data.storage.excel.materials.MaterialsExcelStorage
import domain.data.repository.material.excel.ExcelMaterialRepository
import domain.model.data.excel.ExcelMaterialData

class ExcelMaterialRepositoryImpl(
    private val storage: MaterialsExcelStorage
): ExcelMaterialRepository {
    override fun getData(): ExcelMaterialData {
        val materials = storage.load().map {
            it.toDomain()
        }
        return ExcelMaterialData(materials = materials.toMutableList())
    }
}