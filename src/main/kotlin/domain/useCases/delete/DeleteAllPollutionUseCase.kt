package domain.useCases.delete

import domain.data.repository.pollution.remote.PollutionsRemoteRepository

class DeleteAllPollutionUseCase(
    private val repository: PollutionsRemoteRepository
) {
    fun execute() {
        repository.deleteAll()
    }
}