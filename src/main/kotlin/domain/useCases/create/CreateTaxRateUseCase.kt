package domain.useCases.create

import domain.data.repository.taxRate.remote.TaxRateRemoteRepository
import domain.model.TaxRate
import java.sql.SQLIntegrityConstraintViolationException

class CreateTaxRateUseCase(
    private val repository: TaxRateRemoteRepository
) {
    fun execute(taxRate: TaxRate, sqlException: () -> Unit) {
        try {
            repository.add(taxRate)
        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}