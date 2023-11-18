package data.mappers.excelDataToRemoteImpl

import domain.model.data.excel.ExcelEnterpriseData
import domain.model.data.remote.RemoteEnterpriseData

fun excelEnterprisesToRemote(
    excelData: ExcelEnterpriseData
) = RemoteEnterpriseData(
    enterprises = excelData.enterprises
)