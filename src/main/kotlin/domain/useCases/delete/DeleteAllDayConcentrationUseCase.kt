package domain.useCases.delete

import domain.data.repository.dayConcentration.remote.DayConcentrationsRemoteRepository

class DeleteAllDayConcentrationUseCase(
    private val repository: DayConcentrationsRemoteRepository
) {
    fun execute() {
        repository.deleteAll()
    }
}