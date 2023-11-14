package domain.data.enterprise.excel

import domain.model.data.excel.ExcelEnterprisesData

interface ExcelEnterprisesLoader {
    fun getData(): ExcelEnterprisesData
}