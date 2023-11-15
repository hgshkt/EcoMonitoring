package domain.model.data.excel

import domain.model.Pollution

data class ExcelPollutionData(
    val pollutions: MutableList<Pollution>
)
