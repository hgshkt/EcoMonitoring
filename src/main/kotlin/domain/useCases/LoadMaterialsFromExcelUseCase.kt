package domain.useCases

import domain.data.mappers.toRemote
import domain.data.repository.material.excel.ExcelMaterialRepository
import domain.data.repository.material.remote.MaterialsRemoteRepository

class LoadMaterialsFromExcelUseCase(
    private val remoteRepository: MaterialsRemoteRepository,
    private val excelRepository: ExcelMaterialRepository
) {
    fun execute(
        filePath: String
    ) {
        val excelData = excelRepository.getData(filePath)
        val remoteData = excelData.toRemote()
        remoteRepository.addData(remoteData)
    }
}