package domain.useCases.loadFromExcel

import domain.data.obtained.calculators.DamageCalculator
import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.damageData.DamageDataRemoteRepository
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.Material
import domain.model.Pollution

class LoadPollutionsFromExcelUseCase(
    private val remoteRepository: PollutionsRemoteRepository,
    private val excelRepository: ExcelPollutionRepository,
    private val remoteMaterialRepository: MaterialsRemoteRepository,
    private val riskCalculator: RiskCalculator,
    private val damageCalculator: DamageCalculator,
    private val damageDataRemoteRepository: DamageDataRemoteRepository
) {

    private var pollutions: MutableList<Pollution> = mutableListOf()

    fun execute(
        filePath: String,
        materialNotFoundException: () -> Unit,
        enterpriseNotFoundException: () -> Unit
    ) {
        val damageData = damageDataRemoteRepository.get()
        try {
            pollutions = excelRepository.getData(filePath)
        } catch (e: NullPointerException) {
            enterpriseNotFoundException()
        }
        try {
            pollutions.forEach { pollution ->
                val material = remoteMaterialRepository.getByName(pollution.materialName)
                riskCalculator.calculateRisk(material, pollution)
                damageCalculator.calcDamage(material, pollution, damageData)
            }
        } catch (e: NullPointerException) {
            materialNotFoundException()
        }

        remoteRepository.addData(pollutions)
    }
}