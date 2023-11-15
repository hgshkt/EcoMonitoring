package domain.model.data.remote

import domain.model.Pollution

data class RemotePollutionData(
    val pollutions: MutableList<Pollution>
)
