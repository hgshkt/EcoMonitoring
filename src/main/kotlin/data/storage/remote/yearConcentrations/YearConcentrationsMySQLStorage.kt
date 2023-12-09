package data.storage.remote.yearConcentrations

import data.storage.DatabaseConnectionData
import data.storage.remote.yearConcentrations.model.RemoteYearConcentration

class YearConcentrationsMySQLStorage(
    private val connectionData: DatabaseConnectionData
): YearConcentrationsRemoteStorage {
    override fun getAll(): List<RemoteYearConcentration> {
        TODO("Not yet implemented")
    }

    override fun add(concentrations: List<RemoteYearConcentration>) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}