package data.mappers.remoteDataToDomain

import data.storage.remote.enterprises.EnterpriseRemoteStorage
import data.storage.remote.materials.MaterialRemoteStorage
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution

class MapperRemoteToDomainPollution(
    private val enterpriseStorage: EnterpriseRemoteStorage,
    private val materialStorage: MaterialRemoteStorage
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
            enterpriseName = enterprise?.name ?: "підприємство не знайдено",
            materialName = material?.name ?: "матеріал не знайдено",
            year = pollution.year,
            materialAmount = pollution.materialAmount
        )
    }
}