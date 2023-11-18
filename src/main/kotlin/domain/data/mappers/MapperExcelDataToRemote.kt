package domain.data.mappers

import domain.model.data.excel.ExcelEnterpriseData
import domain.model.data.excel.ExcelMaterialData
import domain.model.data.excel.ExcelPollutionData
import domain.model.data.remote.RemoteEnterpriseData
import domain.model.data.remote.RemoteMaterialData
import domain.model.data.remote.RemotePollutionData

interface MapperExcelDataToRemote {
    fun execute(excelData: ExcelPollutionData): RemotePollutionData

    fun execute(excelData: ExcelMaterialData): RemoteMaterialData

    fun execute(excelData: ExcelEnterpriseData): RemoteEnterpriseData
}