package data.storage.excel.materials

import data.storage.excel.materials.model.ExcelStorageMaterial
import domain.model.Material
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

class MaterialsExcelStorageImpl:  MaterialsExcelStorage{
    override fun load(fileName: String): MutableList<ExcelStorageMaterial> {
        val materials = mutableListOf<ExcelStorageMaterial>()

        FileInputStream(fileName).use { fis ->
            val workbook: Workbook = XSSFWorkbook(fis)
            val sheet: Sheet = workbook.getSheetAt(0)

            for (row in sheet) {
                materials.add(
                    element = ExcelStorageMaterial(
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