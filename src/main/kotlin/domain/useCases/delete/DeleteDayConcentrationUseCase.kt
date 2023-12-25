package domain.useCases.delete

import domain.data.repository.dayConcentration.remote.DayConcentrationsRemoteRepository

class DeleteDayConcentrationUseCase(
    private val repository: DayConcentrationsRemoteRepository
) {
    fun execute(id: Int) {
        repository.deleteById(id)
    }
}