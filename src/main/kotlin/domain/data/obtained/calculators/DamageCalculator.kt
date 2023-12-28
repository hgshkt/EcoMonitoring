package domain.data.obtained.calculators

import domain.model.Material
import domain.model.Pollution

interface DamageCalculator {
    fun calcDamage(
        material: Material,
        pollution: Pollution
    ): Double
}