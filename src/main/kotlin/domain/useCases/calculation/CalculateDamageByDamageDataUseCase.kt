package domain.useCases.calculation

import domain.data.obtained.calculators.DamageCalculator
import domain.model.calculation.DamageData

class CalculateDamageByDamageDataUseCase(
    private val calculator: DamageCalculator
) {
    fun execute(): Double {
        return 0.0//calculator.calcDamage()
    }
}