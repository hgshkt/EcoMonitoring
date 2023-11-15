package domain.useCases

import domain.data.repository.enterprise.excel.ExcelEnterpriseRepository
import domain.model.data.excel.ExcelEnterpriseData

fun getEnterprisesDataFromExcel(
    loader: ExcelEnterpriseRepository
): ExcelEnterpriseData {
    return loader.getData()
}