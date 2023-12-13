package presentation.screens.tables.usecases

import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import domain.useCases.delete.DeleteAllEnterpriseUseCase
import domain.useCases.delete.DeleteEnterpriseByIdUseCase
import domain.useCases.get.GetEnterprisesFromRemoteRepositoryUseCase
import domain.useCases.update.UpdateEnterpriseUseCase

data class EnterpriseTableViewUseCases(
    val getAll: GetEnterprisesFromRemoteRepositoryUseCase
    = GetEnterprisesFromRemoteRepositoryUseCase(
        repository = EnterpriseMySQLRepository(
            storage = EnterprisesMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val delete: DeleteEnterpriseByIdUseCase = DeleteEnterpriseByIdUseCase(
        repository = EnterpriseMySQLRepository(
            storage = EnterprisesMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val deleteAll: DeleteAllEnterpriseUseCase = DeleteAllEnterpriseUseCase(
        repository = EnterpriseMySQLRepository(
            storage = EnterprisesMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val update: UpdateEnterpriseUseCase = UpdateEnterpriseUseCase()
)