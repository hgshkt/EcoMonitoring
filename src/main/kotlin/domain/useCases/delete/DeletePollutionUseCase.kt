package domain.useCases.delete

import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.Pollution

class DeletePollutionUseCase(
    private val repository: PollutionsRemoteRepository
) {
    fun execute(pollution: Pollution) {
        repository.delete(pollution)
    }
}