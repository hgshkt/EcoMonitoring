package data.storage.excel.damageData

import data.storage.excel.damageData.model.ExcelDamageData

interface DamageDataExcelStorage {
    fun load(filePath: String): ExcelDamageData
}