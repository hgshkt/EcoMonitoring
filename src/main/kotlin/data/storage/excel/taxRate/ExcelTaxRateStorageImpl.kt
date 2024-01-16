package data.storage.excel.taxRate

import data.storage.excel.taxRate.model.ExcelTaxRate
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class ExcelTaxRateStorageImpl : ExcelTaxRateStorage {
    override fun load(fileName: String): MutableList<ExcelTaxRate> {
        val taxRates = mutableListOf<ExcelTaxRate>()

        FileInputStream(fileName).use { fis ->
            val workbook = XSSFWorkbook(fis)
            val sheet = workbook.getSheetAt(0)

            for (row in sheet) {
                taxRates.add(
                    ExcelTaxRate(
                        materialName = row.getCell(0).stringCellValue,
                        value = row.getCell(1).numericCellValue
                    )
                )
            }
        }
        return taxRates
    }
}