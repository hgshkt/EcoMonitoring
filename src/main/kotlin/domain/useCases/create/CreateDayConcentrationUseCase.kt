package domain.useCases.create

import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.dayConcentration.remote.DayConcentrationsRemoteRepository
import domain.model.DayConcentration
import java.sql.SQLIntegrityConstraintViolationException

class CreateDayConcentrationUseCase(
    private val repository: DayConcentrationsRemoteRepository,
    private val materialsRemoteRepository: MaterialsRemoteRepository,
    private val riskCalculator: RiskCalculator
) {
    fun execute(concentration: DayConcentration, sqlException: () -> Unit) {
        try {
            val material = materialsRemoteRepository.getByName(concentration.materialName)

            riskCalculator.calculateRisk(material, concentration)

            repository.add(concentration, material)
        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}