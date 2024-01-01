package domain.useCases.get

import domain.data.repository.yearConcentration.remote.YearConcentrationRemoteRepository
import domain.model.YearConcentration

class GetYearConcentrationsFromRemoteRepositoryUseCase(
    private val repository: YearConcentrationRemoteRepository
) {

    fun execute(): MutableList<YearConcentration> {
        return repository.getAll()
    }
}