package domain.useCases.create

import domain.data.obtained.calculators.MaterialCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.yearConcentration.remote.YearConcentrationRemoteRepository
import domain.model.YearConcentration
import java.sql.SQLIntegrityConstraintViolationException

class CreateYearConcentrationUseCase(
    private val repository: YearConcentrationRemoteRepository,
    private val materialRemoteRepository: MaterialsRemoteRepository,
    private val calculator: MaterialCalculator
) {

    fun execute(
        concentration: YearConcentration,
        sqlException: () -> Unit
    ) {
        try {
            val concentrations = repository.getAll()

            repository.add(concentration)

            val materials = materialRemoteRepository.getData().materials

            val year = concentrations.maxOf { it.year }
            val lasYearConcentrations = concentrations.filter { it.year == year }

            lasYearConcentrations.forEach { curConcentration ->
                val material = materials.find {
                    it.name == curConcentration.materialName
                }!!
                calculator.calculate(material, curConcentration)
                materialRemoteRepository.update(material)
            }
        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}