package data.repository.enterprises.remote

import data.mappers.remoteDataToDomain.toDomain
import data.storage.remote.enterprises.EnterpriseRemoteStorage
import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository
import domain.model.data.remote.RemoteEnterpriseData

class EnterpriseMySQLRepository(
    private val storage: EnterpriseRemoteStorage
): EnterprisesRemoteRepository {
    override fun getData(): RemoteEnterpriseData {
        val enterprises = storage.getAll().map { it.toDomain() }
        return RemoteEnterpriseData(enterprises = enterprises.toMutableList())
    }
}