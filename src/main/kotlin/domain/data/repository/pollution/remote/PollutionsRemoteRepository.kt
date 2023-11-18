package domain.data.repository.pollution.remote

import domain.model.data.remote.RemotePollutionData

interface PollutionsRemoteRepository {
    fun getData(): RemotePollutionData

    fun addData(data: RemotePollutionData)
}