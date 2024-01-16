package data.storage.excel.yearConcentration

import data.storage.excel.yearConcentration.model.ExcelYearConcentration
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class ExcelYearConcentrationStorageImpl: YearConcentrationExcelStorage {
    override fun load(fileName: String): MutableList<ExcelYearConcentration> {
        val concentrations = mutableListOf<ExcelYearConcentration>()

        FileInputStream(fileName).use { fis ->
            val workbook: Workbook = XSSFWorkbook(fis)
            val sheet: Sheet = workbook.getSheetAt(0)

            for (row in sheet) {
                concentrations.add(
                    element = ExcelYearConcentration(
                        materialName = row.getCell(0).stringCellValue,
                        year = row.getCell(1).numericCellValue.toInt(),
                        value = row.getCell(2).numericCellValue
                    )
                )
            }
        }
        return concentrations
    }
}