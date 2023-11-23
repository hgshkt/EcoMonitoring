package domain.data.repository.pollution.remote

import domain.model.Pollution
import domain.model.data.remote.RemotePollutionData

interface PollutionsRemoteRepository {
    fun getData(): RemotePollutionData

    fun addData(data: RemotePollutionData)
    fun add(pollution: Pollution)

    fun delete(pollution: Pollution)

    fun deleteAll()
}