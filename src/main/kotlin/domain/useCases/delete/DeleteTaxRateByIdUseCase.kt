package domain.useCases.delete

import domain.data.repository.taxRate.remote.TaxRateRemoteRepository

class DeleteTaxRateByIdUseCase(
    private val repository: TaxRateRemoteRepository
) {
    fun execute(id: Int) {
        repository.deleteById(id)
    }
}