package data.storage.excel.dayConcentrations

import data.storage.excel.dayConcentrations.excel.ExcelDayConcentration
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class ExcelDayConcentrationStorageImpl: ExcelDayConcentrationStorage {
    override fun load(fileName: String): MutableList<ExcelDayConcentration> {
        val concentrations = mutableListOf<ExcelDayConcentration>()

        FileInputStream(fileName).use { fis ->
            val workbook: Workbook = XSSFWorkbook(fis)
            val sheet: Sheet = workbook.getSheetAt(0)

            for (row in sheet) {
                concentrations.add(
                    element = ExcelDayConcentration(
                        id = row.getCell(0).numericCellValue.toInt(),
                        materialId = row.getCell(1).numericCellValue.toInt(),
                        year = row.getCell(2).numericCellValue.toInt(),
                        value = row.getCell(3).numericCellValue
                    )
                )
            }
        }
        return concentrations
    }
}