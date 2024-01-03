package domain.useCases.loadFromExcel

import domain.data.repository.material.excel.ExcelMaterialRepository
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.model.data.remote.RemoteMaterialData

class LoadMaterialsFromExcelUseCase(
    private val remoteRepository: MaterialsRemoteRepository,
    private val excelRepository: ExcelMaterialRepository
) {
    fun execute(
        filePath: String
    ) {
        val materials = excelRepository.load(filePath)
        remoteRepository.addData(RemoteMaterialData(materials))
    }
}