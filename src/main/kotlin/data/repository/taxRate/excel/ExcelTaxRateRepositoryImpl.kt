package data.repository.taxRate.excel

import data.mappers.excel.taxRate.toDomain
import data.storage.excel.taxRate.ExcelTaxRateStorage
import domain.data.repository.taxRate.excel.ExcelTaxRateRepository
import domain.model.TaxRate

class ExcelTaxRateRepositoryImpl(
    private val storage: ExcelTaxRateStorage
) : ExcelTaxRateRepository {
    override fun getData(fileName: String): MutableList<TaxRate> {
        return storage.load(fileName)
            .map { it.toDomain() }
            .toMutableList()
    }
}