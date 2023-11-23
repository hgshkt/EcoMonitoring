package domain.useCases.delete

import domain.data.repository.material.remote.MaterialsRemoteRepository

class DeleteMaterialByIdUseCase(
    private val repository: MaterialsRemoteRepository
) {
    fun execute(id: Int) {
        repository.deleteById(id)
    }
}
