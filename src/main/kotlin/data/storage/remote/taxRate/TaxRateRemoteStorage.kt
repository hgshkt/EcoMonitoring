package data.storage.remote.taxRate

import data.storage.remote.taxRate.model.RemoteTaxRate

interface TaxRateRemoteStorage {
    fun getAll(): List<RemoteTaxRate>

    fun add(taxRate: RemoteTaxRate)

    fun deleteById(id: Int)

    fun deleteAll()
}