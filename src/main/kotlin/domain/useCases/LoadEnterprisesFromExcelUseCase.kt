package domain.useCases

import domain.data.mappers.MapperExcelDataToRemote
import domain.data.repository.enterprise.excel.ExcelEnterpriseRepository
import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository

class LoadEnterprisesFromExcelUseCase(
    private val remoteRepository: EnterprisesRemoteRepository,
    private val excelRepository: ExcelEnterpriseRepository,
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