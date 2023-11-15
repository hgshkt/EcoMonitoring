package domain.data.repository.enterprise.remote

import domain.model.data.remote.RemoteEnterpriseData

interface EnterprisesRemoteRepository {
    fun getData(): RemoteEnterpriseData
}