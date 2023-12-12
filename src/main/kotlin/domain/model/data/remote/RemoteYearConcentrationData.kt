package domain.model.data.remote

import domain.model.Material
import domain.model.YearConcentration

data class RemoteYearConcentrationData(
    val concentrations: MutableList<YearConcentration>,
    val materials: MutableList<Material> = mutableListOf()
)