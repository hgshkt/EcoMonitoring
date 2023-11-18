package data.mappers.excelDataToRemoteImpl

import domain.model.data.excel.ExcelMaterialData
import domain.model.data.excel.ExcelPollutionData
import domain.model.data.remote.RemoteMaterialData

fun excelMaterialsToRemote(
    excelData: ExcelMaterialData
) = RemoteMaterialData(
    materials = excelData.materials
)