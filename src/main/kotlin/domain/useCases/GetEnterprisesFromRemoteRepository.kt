package domain.useCases

import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository
import domain.model.data.remote.RemoteEnterpriseData

class GetEnterprisesFromRemoteRepository(
    private val repository: EnterprisesRemoteRepository
) {
    fun getEnterprisesFromRemoteRepository(): RemoteEnterpriseData {
        return repository.getData()
    }
}