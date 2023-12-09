package domain.useCases.delete

import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository

class DeleteAllYearConcentrationUseCase(
    private val repository: YearConcentrationsRemoteRepository
) {
    fun execute() {
        repository.deleteAll()
    }
}