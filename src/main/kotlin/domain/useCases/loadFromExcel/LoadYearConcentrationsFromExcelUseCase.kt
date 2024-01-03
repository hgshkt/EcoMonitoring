package domain.useCases.loadFromExcel

import domain.data.obtained.calculators.MaterialCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.yearConcentration.excel.ExcelYearConcentrationRepository
import domain.data.repository.yearConcentration.remote.YearConcentrationRemoteRepository

class LoadYearConcentrationsFromExcelUseCase(
    private val remoteRepository: YearConcentrationRemoteRepository,
    private val excelRepository: ExcelYearConcentrationRepository,
    private val materialRemoteRepository: MaterialsRemoteRepository,
    private val calculator: MaterialCalculator
) {

    fun execute(filePath: String) {
        val concentrations = excelRepository.getData(filePath)

        concentrations.forEach {
            remoteRepository.add(it)
        }

        val materials = materialRemoteRepository.getData().materials

        val year = concentrations.maxOf { it.year }
        val lasYearConcentrations = concentrations.filter { it.year == year }

        lasYearConcentrations.forEach { concentration ->
            val material = materials.find {
                it.name == concentration.materialName
            }!!
            calculator.calculate(material, concentration)
            materialRemoteRepository.update(material)
        }
    }
}