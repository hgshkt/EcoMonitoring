package data.storage.excel.taxRate

import data.storage.excel.taxRate.model.ExcelTaxRate

interface ExcelTaxRateStorage {
    fun load(fileName: String): MutableList<ExcelTaxRate>
}