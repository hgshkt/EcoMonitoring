package domain.useCases.loadFromExcel

import domain.data.mappers.toRemote
import domain.data.repository.enterprise.excel.ExcelEnterpriseRepository
import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository

class LoadEnterprisesFromExcelUseCase(
    private val remoteRepository: EnterprisesRemoteRepository,
    private val excelRepository: ExcelEnterpriseRepository
) {
    fun execute(
        filePath: String
    ) {
        val excelData = excelRepository.getData(filePath)
        val remoteData = excelData.toRemote()
        remoteRepository.addData(remoteData)
    }
}