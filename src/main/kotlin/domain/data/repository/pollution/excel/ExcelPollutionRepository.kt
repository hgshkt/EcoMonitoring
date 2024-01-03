package domain.data.repository.pollution.excel

import domain.model.Pollution

interface ExcelPollutionRepository {
    fun getData(fileName: String): MutableList<Pollution>
}