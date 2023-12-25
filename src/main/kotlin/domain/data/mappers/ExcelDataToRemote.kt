package domain.data.mappers

import domain.model.data.excel.ExcelEnterpriseData
import domain.model.data.excel.ExcelMaterialData
import domain.model.data.excel.ExcelPollutionData
import domain.model.data.excel.ExcelDayConcentrationData
import domain.model.data.remote.RemoteEnterpriseData
import domain.model.data.remote.RemoteMaterialData
import domain.model.data.remote.RemotePollutionData
import domain.model.data.remote.RemoteDayConcentrationData

fun ExcelEnterpriseData.toRemote() = RemoteEnterpriseData(
    enterprises = enterprises
)

fun ExcelMaterialData.toRemote() = RemoteMaterialData(
    materials = materials
)

fun ExcelPollutionData.toRemote() = RemotePollutionData(
    pollutions = pollutions
)

fun ExcelDayConcentrationData.toRemote() = RemoteDayConcentrationData(
    concentrations = concentrations
)