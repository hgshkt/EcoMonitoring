package data.storage.excel.enterprises

import data.storage.excel.enterprises.model.ExcelStorageEnterprise
import domain.model.Enterprise

interface EnterpriseExcelStorage {
    fun load(fileName: String): MutableList<ExcelStorageEnterprise>
}