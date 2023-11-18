package domain.data.repository.enterprise.excel

import domain.model.data.excel.ExcelEnterpriseData

interface ExcelEnterpriseRepository {
    fun getData(fileName: String): ExcelEnterpriseData
}