package data.repository.materials.remote

import data.storage.remote.materials.MaterialRemoteStorage
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.data.remote.RemoteMaterialData

class MaterialMySQLRepository(
    private val storage: MaterialRemoteStorage
): PollutionsRemoteRepository {
    override fun getData(): RemoteMaterialData {
        val materials = storage.getAll().map { it.toDomain() }
        return RemoteMaterialData(materials = materials)
    }
}