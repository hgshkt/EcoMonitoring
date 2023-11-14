package domain.data.excel.loader

import domain.model.excelData.ExcelEnterprisesData

interface ExcelEnterprisesLoader {
    fun getData(): ExcelEnterprisesData
}