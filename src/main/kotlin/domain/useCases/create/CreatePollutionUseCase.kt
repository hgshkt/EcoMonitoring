package domain.useCases.create

import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.Pollution
import java.sql.SQLException
import java.sql.SQLIntegrityConstraintViolationException

class CreatePollutionUseCase(
    private val repository: PollutionsRemoteRepository,
    private val riskCalculator: RiskCalculator,
    private val materialsRemoteRepository: MaterialsRemoteRepository
) {
    fun execute(pollution: Pollution, sqlException: () -> Unit) {
        try {

            val material = materialsRemoteRepository.getByName(pollution.materialName)
            riskCalculator.calculateRisk(material, pollution)

            repository.add(pollution)

        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}