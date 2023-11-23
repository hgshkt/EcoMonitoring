package data.repository.enterprises.remote

import data.mappers.domain.enterprise.toRemote
import data.mappers.remote.enterprise.toDomain
import data.storage.remote.enterprises.EnterpriseRemoteStorage
import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository
import domain.model.Enterprise
import domain.model.data.remote.RemoteEnterpriseData

class EnterpriseMySQLRepository(
    private val storage: EnterpriseRemoteStorage
) : EnterprisesRemoteRepository {
    override fun getData(): RemoteEnterpriseData {
        val enterprises = storage.getAll().map { it.toDomain() }
        return RemoteEnterpriseData(enterprises = enterprises.toMutableList())
    }

    override fun addData(data: RemoteEnterpriseData) {
        val enterprises = data.enterprises.map { it.toRemote() }
        storage.add(enterprises)
    }

    override fun add(enterprise: Enterprise) {
        val list = listOf(enterprise.toRemote())
        storage.add(list)
    }

    override fun deleteById(id: Int) {
        storage.deleteById(id)
    }

    override fun deleteAll() {
        storage.deleteAll()
    }
}