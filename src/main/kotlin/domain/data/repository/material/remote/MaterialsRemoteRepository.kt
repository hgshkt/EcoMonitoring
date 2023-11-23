package domain.data.repository.material.remote

import domain.model.Material
import domain.model.data.remote.RemoteMaterialData

interface MaterialsRemoteRepository {
    fun getData(): RemoteMaterialData

    fun addData(data: RemoteMaterialData)

    fun add(material: Material)

    fun deleteById(id: Int)
}