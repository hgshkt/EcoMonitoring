package domain.data.obtained.calculators

import domain.model.Material
import domain.model.DayConcentration

interface RiskCalculator {
    fun calculateRisk(
        material: Material,
        concentration: DayConcentration
    ): DayConcentration
}