package domain.data.repository.taxRate.remote

import domain.model.TaxRate

interface TaxRateRemoteRepository {
    fun getAll(): MutableList<TaxRate>

    fun add(taxRate: TaxRate)

    fun deleteById(id: Int)

    fun deleteAll()
}