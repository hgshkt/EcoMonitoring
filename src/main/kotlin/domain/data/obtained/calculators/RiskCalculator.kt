package domain.data.obtained.calculators

import domain.model.Material
import domain.model.YearConcentration

interface RiskCalculator {
    fun calculateRisk(
        material: Material,
        concentration: YearConcentration
    ): YearConcentration
}