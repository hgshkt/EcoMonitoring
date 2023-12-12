package domain.useCases.loadFromExcel

import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.yearConcentration.excel.ExcelYearConcentrationRepository
import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository
import domain.model.data.remote.RemoteYearConcentrationData

class LoadYearConcentrationsFromExcelUseCase(
    private val remoteRepository: YearConcentrationsRemoteRepository,
    private val excelRepository: ExcelYearConcentrationRepository,
    private val materialsRemoteRepository: MaterialsRemoteRepository,
    private val riskCalculator: RiskCalculator
) {
    fun execute(
        filePath: String
    ) {
        val concentrations = excelRepository.getData(filePath)
        val materials = materialsRemoteRepository.getData()

        val calculatedConcentrations = riskCalculator.calculateRisk(
            materials.materials,
            concentrations.concentrations
        )

        val remoteData = RemoteYearConcentrationData(
            concentrations = calculatedConcentrations,
            materials = materials.materials
        )

        remoteRepository.addData(remoteData)
    }
}