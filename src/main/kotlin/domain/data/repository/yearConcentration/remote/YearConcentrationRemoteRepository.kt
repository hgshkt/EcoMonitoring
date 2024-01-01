package domain.data.repository.yearConcentration.remote

import domain.model.YearConcentration

interface YearConcentrationRemoteRepository {
    fun getAll(): MutableList<YearConcentration>

    fun add(concentration: YearConcentration)

    fun update(concentration: YearConcentration)

    fun deleteById(id: Int)

    fun deleteAll()
}