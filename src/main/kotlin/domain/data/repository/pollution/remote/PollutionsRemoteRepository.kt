package domain.data.repository.pollution.remote

import domain.model.Pollution
import domain.model.data.remote.RemotePollutionData

interface PollutionsRemoteRepository {
    fun getData(): RemotePollutionData

    fun addData(pollutions: MutableList<Pollution>)
    fun add(pollution: Pollution)

    fun delete(pollution: Pollution)

    fun deleteAll()

    fun updateDamage(pollution: Pollution)
}