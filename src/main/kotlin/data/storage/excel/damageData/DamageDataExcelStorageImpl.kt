package data.storage.excel.damageData

import data.storage.excel.damageData.model.ExcelDamageData
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class DamageDataExcelStorageImpl: DamageDataExcelStorage {
    override fun load(fileName: String): ExcelDamageData {
        FileInputStream(fileName).use {fis ->
            val workbook: Workbook = XSSFWorkbook(fis)
            val sheet: Sheet = workbook.getSheetAt(0)

            with(sheet.getRow(1)) {
                return ExcelDamageData(
                    population = getCell(0).numericCellValue.toInt(),
                    P = getCell(1).numericCellValue.toInt(),
                    kf = getCell(2).numericCellValue
                )
            }
        }
    }
}