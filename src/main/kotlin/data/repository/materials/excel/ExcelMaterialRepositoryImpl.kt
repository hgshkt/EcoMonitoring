package data.repository.materials.excel

import data.storage.excel.enterprises.EnterpriseExcelStorage
import domain.data.repository.material.excel.ExcelMaterialRepository
import domain.model.data.excel.ExcelMaterialData

class ExcelMaterialRepositoryImpl(
    val storage: EnterpriseExcelStorage
): ExcelMaterialRepository {
    override fun getData(): ExcelMaterialData {
        val materials = storage.load().map {
            it.toDomain()
        }
        return ExcelMaterialData(materials = materials)
    }
}