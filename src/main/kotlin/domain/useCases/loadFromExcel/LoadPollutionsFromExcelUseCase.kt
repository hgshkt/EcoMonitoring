package domain.useCases.loadFromExcel

import domain.data.mappers.toRemote
import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.data.repository.pollution.remote.PollutionsRemoteRepository

class LoadPollutionsFromExcelUseCase(
    private val remoteRepository: PollutionsRemoteRepository,
    private val excelRepository: ExcelPollutionRepository
) {
    fun execute(
        filePath: String
    ) {
        val excelData = excelRepository.getData(filePath)
        val remoteData = excelData.toRemote()
        remoteRepository.addData(remoteData)
    }
}