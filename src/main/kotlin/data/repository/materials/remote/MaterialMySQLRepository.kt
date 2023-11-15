package data.repository.materials.remote

import data.mappers.remoteDataToDomain.toDomain
import data.storage.remote.materials.MaterialRemoteStorage
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.model.data.remote.RemoteMaterialData

class MaterialMySQLRepository(
    private val storage: MaterialRemoteStorage
): MaterialsRemoteRepository {
    override fun getData(): RemoteMaterialData {
        val materials = storage.getAll().map { it.toDomain() }
        return RemoteMaterialData(materials = materials.toMutableList())
    }
}