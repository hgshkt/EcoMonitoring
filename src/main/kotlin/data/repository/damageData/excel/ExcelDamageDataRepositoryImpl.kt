package data.repository.damageData.excel

import data.mappers.excel.damageData.toDomain
import data.storage.excel.damageData.DamageDataExcelStorage
import domain.data.repository.damageData.DamageDataExcelRepository
import domain.model.DamageData

class ExcelDamageDataRepositoryImpl(
    private val storage: DamageDataExcelStorage
): DamageDataExcelRepository {
    override fun load(fileName: String): DamageData {
        return storage.load(fileName).toDomain()
    }
}