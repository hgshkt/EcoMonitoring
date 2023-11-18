package domain.useCases

import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository
import domain.model.data.remote.RemoteEnterpriseData

class GetEnterprisesFromRemoteRepositoryUseCase(
    private val repository: EnterprisesRemoteRepository
) {
    fun execute(): RemoteEnterpriseData {
        return repository.getData()
    }
}