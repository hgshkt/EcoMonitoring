package data.mappers.excelDataToDomain

import data.storage.excel.enterprises.EnterpriseExcelStorage
import data.storage.excel.materials.MaterialsExcelStorage
import data.storage.excel.pollutions.model.ExcelPollution
import domain.model.Pollution

class MapperExcelToDomainPollution(
    private val enterpriseStorage: EnterpriseExcelStorage,
    private val materialStorage: MaterialsExcelStorage
) {
    fun toDomain(
        pollution: ExcelPollution
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