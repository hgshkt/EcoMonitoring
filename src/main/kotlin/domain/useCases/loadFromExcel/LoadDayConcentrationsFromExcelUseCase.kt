package domain.useCases.loadFromExcel

import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.dayConcentration.excel.ExcelDayConcentrationRepository
import domain.data.repository.dayConcentration.remote.DayConcentrationsRemoteRepository
import domain.model.DayConcentration
import domain.model.data.remote.RemoteDayConcentrationData

class LoadDayConcentrationsFromExcelUseCase(
    private val remoteRepository: DayConcentrationsRemoteRepository,
    private val excelRepository: ExcelDayConcentrationRepository,
    private val materialsRemoteRepository: MaterialsRemoteRepository,
    private val riskCalculator: RiskCalculator
) {
    fun execute(
        filePath: String
    ) {
        val concentrationsData = excelRepository.getData(filePath)
        val materialsData = materialsRemoteRepository.getData()

        val calculatedConcentrations = mutableListOf<DayConcentration>()

        concentrationsData.concentrations.forEach { concentration ->
            val material = materialsData.materials.find { material ->
                material.name == concentration.materialName
            }!!

            calculatedConcentrations.add(riskCalculator.calculateRisk(material, concentration))
        }

        val remoteData = RemoteDayConcentrationData(
            concentrations = calculatedConcentrations,
            materials = materialsData.materials
        )

        remoteRepository.addData(remoteData)
    }
}