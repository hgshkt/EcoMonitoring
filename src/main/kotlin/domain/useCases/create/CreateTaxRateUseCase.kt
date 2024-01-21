package domain.useCases.create

import domain.data.repository.taxRate.remote.TaxRateRemoteRepository
import domain.model.TaxRate

class CreateTaxRateUseCase(
    private val repository: TaxRateRemoteRepository
) {
    fun execute(taxRate: TaxRate) {
        repository.add(taxRate)
    }
}