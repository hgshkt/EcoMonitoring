package data.repository.taxRate.remote

import data.mappers.domain.taxRate.toRemote
import data.mappers.remote.taxRate.toDomain
import data.storage.remote.taxRate.TaxRateRemoteStorage
import domain.data.repository.taxRate.remote.TaxRateRemoteRepository
import domain.model.TaxRate

class TaxRateRemoteRepositoryImpl(
    private val storage: TaxRateRemoteStorage
): TaxRateRemoteRepository {
    override fun getAll(): MutableList<TaxRate> {
        return storage.getAll().map { it.toDomain() }.toMutableList()
    }

    override fun add(taxRate: TaxRate) {
        storage.add(taxRate.toRemote())
    }

    override fun deleteById(id: Int) {
        storage.deleteById(id)
    }

    override fun deleteAll() {
        storage.deleteAll()
    }
}