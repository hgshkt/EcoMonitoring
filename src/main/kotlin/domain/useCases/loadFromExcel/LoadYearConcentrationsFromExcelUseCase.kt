package domain.useCases.loadFromExcel

import domain.data.mappers.toRemote
import domain.data.repository.yearConcentration.excel.ExcelYearConcentrationRepository
import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository

class LoadYearConcentrationsFromExcelUseCase(
    private val remoteRepository: YearConcentrationsRemoteRepository,
    private val excelRepository: ExcelYearConcentrationRepository
) {
    fun execute(
        filePath: String
    ) {
        val excelData = excelRepository.getData(filePath)
        val remoteData = excelData.toRemote()
        remoteRepository.addData(remoteData)
    }
}