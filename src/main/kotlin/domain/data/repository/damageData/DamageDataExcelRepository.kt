package domain.data.repository.damageData

import domain.model.DamageData

interface DamageDataExcelRepository {
    fun load(fileName: String): DamageData
}