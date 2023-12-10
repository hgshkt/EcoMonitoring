package domain.data.obtained.calculators

import domain.model.Material
import domain.model.YearConcentration

interface RiskCalculator {
    fun calculateRisk(
        materials: MutableList<Material>,
        concentrations: MutableList<YearConcentration>
    ): MutableList<YearConcentration>
}