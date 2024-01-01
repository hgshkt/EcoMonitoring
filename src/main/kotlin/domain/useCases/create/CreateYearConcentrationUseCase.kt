package domain.useCases.create

import domain.data.repository.yearConcentration.remote.YearConcentrationRemoteRepository
import domain.model.YearConcentration
import java.sql.SQLIntegrityConstraintViolationException

class CreateYearConcentrationUseCase(
    private val repository: YearConcentrationRemoteRepository
) {

    fun execute(
        concentration: YearConcentration,
        sqlException: () -> Unit
    ) {
        try {
            repository.add(concentration)
        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}