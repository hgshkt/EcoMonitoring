package domain.useCases.delete

import domain.data.repository.material.remote.MaterialsRemoteRepository

class DeleteAllMaterialUseCase(
    private val repository: MaterialsRemoteRepository
) {
    fun execute() {
        repository.deleteAll()
    }
}