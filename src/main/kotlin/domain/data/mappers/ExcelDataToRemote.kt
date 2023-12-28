package domain.data.mappers

import domain.model.data.excel.ExcelEnterpriseData
import domain.model.data.excel.ExcelMaterialData
import domain.model.data.excel.ExcelPollutionData
import domain.model.data.remote.RemoteEnterpriseData
import domain.model.data.remote.RemoteMaterialData
import domain.model.data.remote.RemotePollutionData

fun ExcelEnterpriseData.toRemote() = RemoteEnterpriseData(
    enterprises = enterprises
)

fun ExcelMaterialData.toRemote() = RemoteMaterialData(
    materials = materials
)

fun ExcelPollutionData.toRemote() = RemotePollutionData(
    pollutions = pollutions
)