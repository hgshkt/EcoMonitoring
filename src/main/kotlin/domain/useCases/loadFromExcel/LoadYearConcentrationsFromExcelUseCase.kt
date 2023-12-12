package domain.useCases.loadFromExcel

import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.yearConcentration.excel.ExcelYearConcentrationRepository
import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository
import domain.model.YearConcentration
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
        val concentrationsData = excelRepository.getData(filePath)
        val materialsData = materialsRemoteRepository.getData()

        val calculatedConcentrations = mutableListOf<YearConcentration>()

        concentrationsData.concentrations.forEach { concentration ->
            val material = materialsData.materials.find { material ->
                material.name == concentration.materialName
            }!!
            riskCalculator.calculateRisk(material, concentration)
        }

        val remoteData = RemoteYearConcentrationData(
            concentrations = calculatedConcentrations,
            materials = materialsData.materials
        )

        remoteRepository.addData(remoteData)
    }
}