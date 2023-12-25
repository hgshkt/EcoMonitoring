package domain.useCases.get

import domain.data.repository.dayConcentration.remote.DayConcentrationsRemoteRepository
import domain.model.data.remote.RemoteDayConcentrationData

class GetDayConcentrationsFromRemoteRepositoryUseCase(
    private val repository: DayConcentrationsRemoteRepository
) {
    fun execute(): RemoteDayConcentrationData {
        return repository.getData()
    }
}