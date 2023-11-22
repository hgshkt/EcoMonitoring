package domain.useCases.get

import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.model.data.remote.RemoteMaterialData

class GetMaterialsFromRemoteRepositoryUseCase(
    private val repository: MaterialsRemoteRepository
) {
    fun execute(): RemoteMaterialData {
        return repository.getData()
    }
}