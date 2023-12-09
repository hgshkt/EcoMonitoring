package data.storage.remote.yearConcentrations

import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import domain.model.YearConcentration

class YearConcentrationsMySQLStorage: YearConcentrationsRemoteStorage {
    override fun getAll(): List<RemoteYearConcentration> {
        TODO("Not yet implemented")
    }

    override fun add(concentrations: List<RemoteYearConcentration>) {
        TODO("Not yet implemented")
    }

    override fun delete(concentration: YearConcentration) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}