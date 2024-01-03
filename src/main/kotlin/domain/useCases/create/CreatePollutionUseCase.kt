package domain.useCases.create

import domain.data.obtained.calculators.DamageCalculator
import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.damageData.DamageDataRemoteRepository
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.Pollution
import java.sql.SQLIntegrityConstraintViolationException

class CreatePollutionUseCase(
    private val repository: PollutionsRemoteRepository,
    private val riskCalculator: RiskCalculator,
    private val materialsRemoteRepository: MaterialsRemoteRepository,
    private val damageCalculator: DamageCalculator,
    private val damageDataRemoteRepository: DamageDataRemoteRepository
) {
    fun execute(pollution: Pollution, sqlException: () -> Unit) {
        try {

            val material = materialsRemoteRepository.getByName(pollution.materialName)
            val damageData = damageDataRemoteRepository.get()

            riskCalculator.calculateRisk(material, pollution)
            damageCalculator.calcDamage(material, pollution, damageData)

            repository.add(pollution)

        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}