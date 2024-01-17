package domain.useCases.get

import domain.data.repository.taxRate.remote.TaxRateRemoteRepository
import domain.model.TaxRate

class GetTaxRatesFromRemoteRepository(
    private val repository: TaxRateRemoteRepository
) {
    fun execute(): MutableList<TaxRate> {
        return repository.getAll()
    }
}