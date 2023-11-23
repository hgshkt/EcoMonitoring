package domain.useCases.delete

import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository

class DeleteAllEnterpriseUseCase(
    private val repository: EnterprisesRemoteRepository
) {
    fun execute() {
        repository.deleteAll()
    }
}