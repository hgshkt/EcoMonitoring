package data.repository.materials.excel

import data.mappers.excel.material.toDomain
import data.storage.excel.materials.MaterialsExcelStorage
import domain.data.repository.material.excel.ExcelMaterialRepository
import domain.model.Material
import domain.model.data.excel.ExcelMaterialData

class ExcelMaterialRepositoryImpl(
    private val storage: MaterialsExcelStorage
): ExcelMaterialRepository {
    override fun load(fileName: String): MutableList<Material> {
        return storage.load(fileName).map {
            it.toDomain()
        }.toMutableList()
    }
}