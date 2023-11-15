package data.storage.excel.materials

import data.storage.excel.materials.model.ExcelMaterial
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class MaterialsExcelStorageImpl(
    private val fileName: String
):  MaterialsExcelStorage{
    override fun load(): MutableList<ExcelMaterial> {
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
                    )
                )
            }
        }
        return materials
    }
}