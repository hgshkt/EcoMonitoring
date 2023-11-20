package data.mappers.excelDataToDomain

import data.storage.excel.pollutions.model.ExcelPollution
import data.storage.remote.enterprises.EnterpriseRemoteStorage
import data.storage.remote.materials.MaterialRemoteStorage
import domain.model.Pollution

class MapperExcelToDomainPollution(
    private val enterpriseStorage: EnterpriseRemoteStorage,
    private val materialStorage: MaterialRemoteStorage
) {
    fun toDomain(
        pollution: ExcelPollution
    ): Pollution {
        val enterprise = enterpriseStorage.getById(
            id = pollution.enterpriseName
        )
        val material = materialStorage.getById(
            id = pollution.materialName
        )

        return Pollution(
            id = pollution.id,
            enterpriseName = enterprise?.name ?: "підприємство не знайдено",
            materialName = material?.name ?: "матеріал не знайдено",
            year = pollution.year,
            materialAmount = pollution.materialAmount
        )
    }
}