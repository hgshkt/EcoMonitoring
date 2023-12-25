package domain.data.obtained.calculators

import domain.model.Material
import domain.model.DayConcentration

interface DamageCalculator {
    fun calcDamage(
        material: Material,
        concentration: DayConcentration
    ): Double
}