package presentation.screens.tables.usecases

import data.repository.damageData.remote.DamageDataRemoteRepositoryImpl
import data.storage.DatabaseConnectionData
import data.storage.remote.damageData.DamageDataRemoteStorageImpl
import domain.useCases.delete.DeleteDamageDataUseCase
import domain.useCases.get.GetDamageDataFromRemoteRepositoryUseCase

data class DamageDataTableViewUseCases(
    val get: GetDamageDataFromRemoteRepositoryUseCase
    = GetDamageDataFromRemoteRepositoryUseCase(
        remoteRepository = DamageDataRemoteRepositoryImpl(
            storage = DamageDataRemoteStorageImpl(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val delete: DeleteDamageDataUseCase = DeleteDamageDataUseCase(
        repository = DamageDataRemoteRepositoryImpl(
            storage = DamageDataRemoteStorageImpl(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)
