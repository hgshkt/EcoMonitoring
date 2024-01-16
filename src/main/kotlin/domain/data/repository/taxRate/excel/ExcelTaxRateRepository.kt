package domain.data.repository.taxRate.excel

import domain.model.TaxRate

interface ExcelTaxRateRepository {
    fun getData(fileName: String): MutableList<TaxRate>
}