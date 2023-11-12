package data

import domain.model.Material
import java.io.FileInputStream
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook

fun loadMaterials(
    fileName: String
): MutableList<Material> {
    val materials = mutableListOf<Material>()

    FileInputStream(fileName).use { fis ->
        val workbook: Workbook = XSSFWorkbook(fis)
        val sheet: Sheet = workbook.getSheetAt(0)

        for (row in sheet) {
            materials.add(
                element = Material(
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