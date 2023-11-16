package domain.useCases

import domain.data.repository.enterprise.excel.ExcelEnterpriseRepository
import domain.model.data.excel.ExcelEnterpriseData

class GetEnterprisesDataFromExcel(
    private val repository: ExcelEnterpriseRepository
) {
    fun getEnterprisesDataFromExcel(): ExcelEnterpriseData {
        return repository.getData()
    }
}