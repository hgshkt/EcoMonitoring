package domain.data.repository.damageData

import domain.model.DamageData

interface DamageDataCalculator {
    fun calculate(data: DamageData): DamageData
}