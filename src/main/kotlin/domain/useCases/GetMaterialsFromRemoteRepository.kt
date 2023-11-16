package domain.useCases

import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.model.data.remote.RemoteMaterialData

class GetMaterialsFromRemoteRepository(
    private val repository: MaterialsRemoteRepository
) {
    fun getMaterialsFromRemoteRepository(): RemoteMaterialData {
        return repository.getData()
    }
}