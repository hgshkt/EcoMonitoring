package domain.useCases

import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.data.remote.RemotePollutionData

class GetPollutionsFromRemoteRepositoryUseCase(
    private val repository: PollutionsRemoteRepository
) {
    fun execute(): RemotePollutionData {
        return repository.getData()
    }
}