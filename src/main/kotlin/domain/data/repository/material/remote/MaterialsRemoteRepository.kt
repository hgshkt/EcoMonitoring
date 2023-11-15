package domain.data.repository.material.remote

import domain.model.Material
import domain.model.Pollution
import domain.model.data.remote.RemoteMaterialData

interface MaterialsRemoteRepository {
    fun getData(): RemoteMaterialData
}