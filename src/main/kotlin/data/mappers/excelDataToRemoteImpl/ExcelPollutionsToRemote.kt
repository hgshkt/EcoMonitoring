package data.mappers.excelDataToRemoteImpl

import domain.model.data.excel.ExcelPollutionData
import domain.model.data.remote.RemotePollutionData

fun excelPollutionToRemote(
    excelData: ExcelPollutionData
) = RemotePollutionData(
    pollutions = excelData.pollutions
)
