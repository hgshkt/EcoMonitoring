package domain.useCases.create

import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository
import domain.model.Enterprise
import java.sql.SQLIntegrityConstraintViolationException

class CreateEnterpriseUseCase(
    private val repository: EnterprisesRemoteRepository = EnterpriseMySQLRepository(
        storage = EnterprisesMySQLStorage(
            connectionData = DatabaseConnectionData()
        )
    )
) {
    fun execute(enterprise: Enterprise, sqlException: () -> Unit) {
        try {
            repository.add(enterprise)
        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}