package domain.useCases.get

import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.data.remote.RemotePollutionData

class GetPollutionsFromRemoteRepositoryUseCase(
    private val repository: PollutionsRemoteRepository
) {
    fun execute(): RemotePollutionData {
        return RemotePollutionData(repository.getAll())
    }
}