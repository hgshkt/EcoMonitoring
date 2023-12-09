package data.storage.excel.yearConcentrations

import data.storage.excel.materials.model.ExcelMaterial
import data.storage.excel.yearConcentrations.excel.ExcelYearConcentration
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class ExcelYearConcentrationStorageImpl: ExcelYearConcentrationStorage {
    override fun load(fileName: String): MutableList<ExcelYearConcentration> {
        val concentrations = mutableListOf<ExcelYearConcentration>()

        FileInputStream(fileName).use { fis ->
            val workbook: Workbook = XSSFWorkbook(fis)
            val sheet: Sheet = workbook.getSheetAt(0)

            for (row in sheet) {
                concentrations.add(
                    element = ExcelYearConcentration(
                        id = row.getCell(0).numericCellValue.toInt(),
                        materialId = row.getCell(1).numericCellValue.toInt(),
                        value = row.getCell(2).numericCellValue,
                        year = row.getCell(3).numericCellValue.toInt()
                    )
                )
            }
        }
        return concentrations
    }
}