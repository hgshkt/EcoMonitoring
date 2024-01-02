package domain.useCases.loadFromExcel

import domain.data.repository.damageData.DamageDataCalculator
import domain.data.repository.damageData.DamageDataExcelRepository
import domain.data.repository.damageData.DamageDataRemoteRepository

class LoadDamageDataFromExcelUseCase(
    private val excelRepository: DamageDataExcelRepository,
    private val remoteRepository: DamageDataRemoteRepository,
    private val calculator: DamageDataCalculator
) {
    fun execute(fileName: String) {
        val excelData = excelRepository.load(fileName)
        val remoteData = calculator.calculate(excelData)
        remoteRepository.put(remoteData)
    }
}