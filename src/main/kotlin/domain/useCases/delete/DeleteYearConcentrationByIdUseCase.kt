package domain.useCases.delete

import domain.data.repository.yearConcentration.remote.YearConcentrationRemoteRepository

class DeleteYearConcentrationByIdUseCase(
    private val repository: YearConcentrationRemoteRepository
) {

    fun execute(id: Int) {
        repository.deleteById(id)
    }
}