package domain.useCases.delete

import domain.data.repository.taxRate.remote.TaxRateRemoteRepository

class DeleteAllTaxRateUseCases(
    private val repository: TaxRateRemoteRepository
) {
    fun execute() {
        repository.deleteAll()
    }
}