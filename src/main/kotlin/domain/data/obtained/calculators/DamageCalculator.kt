package domain.data.obtained.calculators

import domain.model.DamageData
import domain.model.Material
import domain.model.Pollution
import domain.model.YearConcentration

interface DamageCalculator {
    fun calcDamage(
        material: Material,
        pollution: Pollution,
        damageData: DamageData
    ): Double
}