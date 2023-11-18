package domain.useCases

import domain.data.mappers.MapperExcelDataToRemote
import domain.data.repository.material.excel.ExcelMaterialRepository
import domain.data.repository.material.remote.MaterialsRemoteRepository

class LoadMaterialsFromExcelUseCase(
    private val remoteRepository: MaterialsRemoteRepository,
    private val excelRepository: ExcelMaterialRepository,
    private val mapper: MapperExcelDataToRemote
) {
    fun execute(
        filePath: String
    ) {
        val excelData = excelRepository.getData(filePath)
        val remoteData = mapper.execute(excelData)
        remoteRepository.addData(remoteData)
    }
}