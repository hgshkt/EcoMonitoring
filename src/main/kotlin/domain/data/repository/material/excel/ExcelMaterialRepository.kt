package domain.data.repository.material.excel

import domain.model.Material

interface ExcelMaterialRepository {
    fun load(fileName: String): MutableList<Material>
}