package domain.useCases.delete

import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository

class DeleteEnterpriseByIdUseCase(
    private val repository: EnterprisesRemoteRepository
) {
    fun execute(id: Int) {
        repository.deleteById(id)
    }
}