package data.mappers.excelDataToRemoteImpl

import domain.data.mappers.MapperExcelDataToRemote
import domain.model.data.excel.ExcelEnterpriseData
import domain.model.data.excel.ExcelMaterialData
import domain.model.data.excel.ExcelPollutionData
import domain.model.data.remote.RemoteEnterpriseData
import domain.model.data.remote.RemoteMaterialData
import domain.model.data.remote.RemotePollutionData

class MapperExcelDataToRemoteImpl: MapperExcelDataToRemote {
    override fun execute(excelData: ExcelPollutionData): RemotePollutionData {
        return excelPollutionToRemote(excelData)
    }

    override fun execute(excelData: ExcelMaterialData): RemoteMaterialData {
        return excelMaterialsToRemote(excelData)
    }

    override fun execute(excelData: ExcelEnterpriseData): RemoteEnterpriseData {
        return excelEnterprisesToRemote(excelData)
    }
}