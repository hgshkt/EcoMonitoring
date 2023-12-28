package domain.data.obtained.calculators

import domain.model.Material
import domain.model.Pollution

interface RiskCalculator {
    fun calculateRisk(
        material: Material,
        pollution: Pollution
    ): Pollution
}