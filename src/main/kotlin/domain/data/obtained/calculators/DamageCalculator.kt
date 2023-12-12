package domain.data.obtained.calculators

import domain.model.Material
import domain.model.YearConcentration

interface DamageCalculator {
    fun calcDamage(
        material: Material,
        concentration: YearConcentration
    ): Double
}