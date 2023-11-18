package domain.useCases

import domain.data.mappers.MapperExcelDataToRemote
import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.data.repository.pollution.remote.PollutionsRemoteRepository

class LoadPollutionsFromExcelUseCase(
    private val remoteRepository: PollutionsRemoteRepository,
    private val excelRepository: ExcelPollutionRepository,
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