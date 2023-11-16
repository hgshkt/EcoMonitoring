package domain.useCases

import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.data.remote.RemotePollutionData

class GetPollutionsFromRemoteRepository(
    private val repository: PollutionsRemoteRepository
) {
    fun getPollutionsFromRemoteRepository(): RemotePollutionData {
        return repository.getData()
    }
}