package domain.useCases.get

import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository
import domain.model.data.remote.RemoteYearConcentrationData

class GetYearConcentrationsFromRemoteRepositoryUseCase(
    private val repository: YearConcentrationsRemoteRepository
) {
    fun execute(): RemoteYearConcentrationData {
        return repository.getData()
    }
}