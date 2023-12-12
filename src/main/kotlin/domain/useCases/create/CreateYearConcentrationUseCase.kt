package domain.useCases.create

import data.repository.yearConcentrations.remote.YearConcentrationMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.yearConcentrations.YearConcentrationsMySQLStorage
import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository
import domain.model.YearConcentration

class CreateYearConcentrationUseCase(
    private val repository: YearConcentrationsRemoteRepository = YearConcentrationMySQLRepository(
        storage = YearConcentrationsMySQLStorage(
            connectionData = DatabaseConnectionData()
        )
    )
) {
    fun execute(concentration: YearConcentration) {
        repository.add(concentration)
    }
}