package domain.model.data.remote

import domain.model.Material
import domain.model.DayConcentration

data class RemoteDayConcentrationData(
    val concentrations: MutableList<DayConcentration>,
    val materials: MutableList<Material> = mutableListOf()
)