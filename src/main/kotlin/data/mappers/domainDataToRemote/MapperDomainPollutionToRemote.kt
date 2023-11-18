package data.mappers.domainDataToRemote

import data.storage.remote.enterprises.EnterpriseRemoteStorage
import data.storage.remote.materials.MaterialRemoteStorage
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution

class MapperDomainPollutionToRemote(
    private val materialStorage: MaterialRemoteStorage,
    private val enterpriseStorage: EnterpriseRemoteStorage
) {
    fun execute(pollution: Pollution): RemotePollution {
        val material = materialStorage.getByName(pollution.materialName)
        val enterprise = enterpriseStorage.getByName(pollution.enterpriseName)

        return RemotePollution(
            id = pollution.id,
            enterpriseId = enterprise?.id ?: -1,
            materialId = material?.id ?: -1,
            year = pollution.year,
            materialAmount = pollution.materialAmount
        )
    }
}