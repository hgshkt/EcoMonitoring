package data.repository.materials.remote

import data.mappers.domain.material.toRemote
import data.mappers.remote.material.toDomain
import data.storage.remote.materials.MaterialRemoteStorage
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.model.Material
import domain.model.data.remote.RemoteMaterialData

class MaterialMySQLRepository(
    private val storage: MaterialRemoteStorage
): MaterialsRemoteRepository {
    override fun getData(): RemoteMaterialData {
        val materials = storage.getAll().map { it.toDomain() }
        return RemoteMaterialData(materials = materials.toMutableList())
    }

    override fun addData(data: RemoteMaterialData) {
        val materials = data.materials.map { it.toRemote() }
        storage.add(materials)
    }

    override fun add(material: Material) {
        val list = listOf(material.toRemote())
        storage.add(list)
    }

    override fun deleteById(id: Int) {
        storage.deleteById(id)
    }

    override fun deleteAll() {
        storage.deleteAll()
    }
}