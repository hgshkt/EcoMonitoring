package data.storage.excel.enterprises

import data.storage.excel.enterprises.model.ExcelStorageEnterprise
import domain.model.Enterprise
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class EnterpriseExcelStorageImpl: EnterpriseExcelStorage {
    override fun load(fileName: String): MutableList<ExcelStorageEnterprise> {
        val enterprises = mutableListOf<ExcelStorageEnterprise>()

        FileInputStream(fileName).use { fis ->
            val workbook: Workbook = XSSFWorkbook(fis)
            val sheet: Sheet = workbook.getSheetAt(0)

            for (row in sheet) {
                enterprises.add(
                    element = ExcelStorageEnterprise(
                        id = row.getCell(0).numericCellValue.toInt(),
                        name = row.getCell(1).stringCellValue,
                        activity = row.getCell(2).stringCellValue,
                        belonging = row.getCell(3).stringCellValue,
                        location = row.getCell(4).stringCellValue
                    )
                )
            }
        }
        return enterprises
    }
}