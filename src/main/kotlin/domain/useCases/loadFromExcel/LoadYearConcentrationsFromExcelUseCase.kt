package domain.useCases.loadFromExcel

import domain.data.repository.yearConcentration.excel.ExcelYearConcentrationRepository
import domain.data.repository.yearConcentration.remote.YearConcentrationRemoteRepository

class LoadYearConcentrationsFromExcelUseCase(
    private val remoteRepository: YearConcentrationRemoteRepository,
    private val excelRepository: ExcelYearConcentrationRepository
) {

    fun execute(filePath: String) {
        excelRepository.getData(filePath)
            .forEach {
                remoteRepository.add(it)
            }
    }
}