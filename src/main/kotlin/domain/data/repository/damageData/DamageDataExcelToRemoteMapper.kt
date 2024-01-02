package domain.data.repository.damageData

import data.storage.remote.damageData.model.RemoteDamageData
import domain.model.DamageData

interface DamageDataCalculator {
    fun calculate(excelData: DamageData): DamageData
}