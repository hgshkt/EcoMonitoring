package data.obtained

import domain.data.obtained.calculators.DamageCalculator
import domain.model.DamageData
import domain.model.Material
import domain.model.Pollution

class DamageCalculatorImpl: DamageCalculator {
    override fun calcDamage(
        material: Material,
        pollution: Pollution,
        damageData: DamageData
    ): Double {
        val damage = pollution.materialAmount * 1.1 * damageData.P * material.Ai * damageData.kt * material.Kzi
        pollution.damage = damage
        return damage
    }
}