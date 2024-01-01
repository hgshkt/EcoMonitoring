package domain.useCases.delete

import domain.data.repository.yearConcentration.remote.YearConcentrationRemoteRepository

class DeleteAllYearConcentrationUseCase(
    private val repository: YearConcentrationRemoteRepository
) {

    fun execute() {
        repository.deleteAll()
    }
}