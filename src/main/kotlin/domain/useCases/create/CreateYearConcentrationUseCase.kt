package domain.useCases.create

import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository
import domain.model.YearConcentration
import java.sql.SQLIntegrityConstraintViolationException

class CreateYearConcentrationUseCase(
    private val repository: YearConcentrationsRemoteRepository,
    private val materialsRemoteRepository: MaterialsRemoteRepository,
    private val riskCalculator: RiskCalculator
) {
    fun execute(concentration: YearConcentration, sqlException: () -> Unit) {
        try {
            val material = materialsRemoteRepository.getByName(concentration.materialName)

            riskCalculator.calculateRisk(material, concentration)

            repository.add(concentration, material)
        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}