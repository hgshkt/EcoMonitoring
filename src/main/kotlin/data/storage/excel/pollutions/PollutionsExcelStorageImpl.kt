package data.storage.excel.pollutions

import data.storage.excel.pollutions.model.ExcelPollution
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class PollutionsExcelStorageImpl: PollutionsExcelStorage {
    override fun load(fileName: String): MutableList<ExcelPollution> {
        val pollutions = mutableListOf<ExcelPollution>()

        FileInputStream(fileName).use { fis ->
            val workbook: Workbook = XSSFWorkbook(fis)
            val sheet: Sheet = workbook.getSheetAt(0)

            for (row in sheet) {
                pollutions.add(
                    element = ExcelPollution(
                        enterpriseName = row.getCell(0).stringCellValue,
                        materialName = row.getCell(1).stringCellValue,
                        materialAmount = row.getCell(2).numericCellValue,
                        year = row.getCell(3).numericCellValue.toInt(),
                        concentration = row.getCell(4).numericCellValue
                    )
                )
            }
        }
        return pollutions
    }
}