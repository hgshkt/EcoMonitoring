package data.storage.excel.enterprises

import data.storage.excel.enterprises.model.ExcelEnterprise

interface EnterpriseExcelStorage {
    fun load(fileName: String): MutableList<ExcelEnterprise>
}