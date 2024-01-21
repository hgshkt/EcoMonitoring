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
        if (damageData.P == -1 || damageData.kt == -1.0 || damageData.kf == -1.0
            || damageData.knas == -1.0 || damageData.population == -1
            || material.Ai == -1.0)
            return -1.0
        val damage = m(material, pollution) * 1.1 * damageData.P * material.Ai * damageData.kt * material.Kzi
        pollution.damage = damage
        return damage
    }

    private fun m(material: Material, pollution: Pollution): Double {
        return 3.6 * 0.001 * (pollution.materialAmount - material.gdk) * 365 * 24
    }
}