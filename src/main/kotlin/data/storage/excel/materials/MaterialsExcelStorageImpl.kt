package data.storage.excel.materials

import data.storage.excel.materials.model.ExcelMaterial
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class MaterialsExcelStorageImpl:  MaterialsExcelStorage{
    override fun load(fileName: String): MutableList<ExcelMaterial> {
        val materials = mutableListOf<ExcelMaterial>()

        FileInputStream(fileName).use { fis ->
            val workbook: Workbook = XSSFWorkbook(fis)
            val sheet: Sheet = workbook.getSheetAt(0)

            for (row in sheet) {
                materials.add(
                    element = ExcelMaterial(
                        id = row.getCell(0).numericCellValue.toInt(),
                        name = row.getCell(1).stringCellValue,
                        gdk = row.getCell(2).numericCellValue,
                        dangerClass = row.getCell(3).numericCellValue.toInt(),
                        RfC = row.getCell(4).numericCellValue,
                        organ = row.getCell(5).stringCellValue,
                        gdv = row.getCell(6).numericCellValue.toInt(),
                        massEmissions = row.getCell(7).numericCellValue.toInt()
                    )
                )
            }
        }
        return materials
    }
}