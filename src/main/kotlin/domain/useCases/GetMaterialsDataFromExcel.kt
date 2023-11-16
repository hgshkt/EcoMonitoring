package domain.useCases

import domain.data.repository.material.excel.ExcelMaterialRepository
import domain.model.data.excel.ExcelMaterialData

class GetMaterialsDataFromExcel(
   private val repository: ExcelMaterialRepository
) {
    fun getMaterialsDataFromExcel(): ExcelMaterialData {
        return repository.getData()
    }
}