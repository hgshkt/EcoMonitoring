package domain.useCases.loadFromExcel

import domain.data.obtained.calculators.DamageCalculator
import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.damageData.DamageDataRemoteRepository
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.data.repository.pollution.remote.PollutionsRemoteRepository

class LoadPollutionsFromExcelUseCase(
    private val remoteRepository: PollutionsRemoteRepository,
    private val excelRepository: ExcelPollutionRepository,
    private val remoteMaterialRepository: MaterialsRemoteRepository,
    private val riskCalculator: RiskCalculator,
    private val damageCalculator: DamageCalculator,
    private val damageDataRemoteRepository: DamageDataRemoteRepository
) {
    fun execute(
        filePath: String
    ) {
        val damageData = damageDataRemoteRepository.get()
        val pollutions = excelRepository.getData(filePath)

        pollutions.forEach { pollution ->
            val material = remoteMaterialRepository.getByName(pollution.materialName)
            riskCalculator.calculateRisk(material, pollution)
            damageCalculator.calcDamage(material, pollution, damageData)
        }

        remoteRepository.addData(pollutions)
    }
}