package domain.data.repository.enterprise.remote

import domain.model.Enterprise
import domain.model.data.remote.RemoteEnterpriseData

interface EnterprisesRemoteRepository {
    fun getData(): RemoteEnterpriseData

    fun addData(data: RemoteEnterpriseData)

    fun add(enterprise: Enterprise)

    fun update(enterprise: Enterprise)

    fun deleteById(id: Int)

    fun deleteAll()
}