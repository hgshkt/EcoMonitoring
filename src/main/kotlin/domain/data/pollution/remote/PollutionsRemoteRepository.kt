package domain.data.pollution.remote

import domain.model.Pollution

interface PollutionsRemoteRepository {
    fun getData(): MutableList<Pollution>
}