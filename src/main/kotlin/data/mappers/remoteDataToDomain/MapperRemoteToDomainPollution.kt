package data.mappers.remoteDataToDomain

import data.storage.excel.enterprises.EnterpriseExcelStorage
import data.storage.excel.materials.MaterialsExcelStorage
import data.storage.excel.pollutions.model.ExcelPollution
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution

class MapperRemoteToDomainPollution(
    private val enterpriseStorage: EnterpriseExcelStorage,
    private val materialStorage: MaterialsExcelStorage
) {
    fun toDomain(
        pollution: RemotePollution
    ): Pollution {
        val enterprise = enterpriseStorage.getById(
            id = pollution.enterpriseId
        )
        val material = materialStorage.getById(
            id = pollution.materialId
        )

        return Pollution(
            id = pollution.id,
            enterpriseName = enterprise.name,
            materialName = material.name,
            year = pollution.year,
            materialAmount = pollution.materialAmount
        )
    }
}