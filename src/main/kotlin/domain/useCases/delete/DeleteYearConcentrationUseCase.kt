package domain.useCases.delete

import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository

class DeleteYearConcentrationUseCase(
    private val repository: YearConcentrationsRemoteRepository
) {
    fun execute(id: Int) {
        repository.deleteById(id)
    }
}