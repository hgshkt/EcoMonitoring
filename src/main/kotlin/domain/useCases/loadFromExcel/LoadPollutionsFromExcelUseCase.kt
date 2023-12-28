package domain.useCases.loadFromExcel

import domain.data.mappers.toRemote
import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.pollution.excel.ExcelPollutionRepository
import domain.data.repository.pollution.remote.PollutionsRemoteRepository

class LoadPollutionsFromExcelUseCase(
    private val remoteRepository: PollutionsRemoteRepository,
    private val excelRepository: ExcelPollutionRepository,
    private val remoteMaterialRepository: MaterialsRemoteRepository,
    private val riskCalculator: RiskCalculator
) {
    fun execute(
        filePath: String
    ) {
        val excelData = excelRepository.getData(filePath)

        excelData.pollutions.forEach { pollution ->
            val material = remoteMaterialRepository.getByName(pollution.materialName)
            riskCalculator.calculateRisk(material, pollution)
        }

        val remoteData = excelData.toRemote()
        remoteRepository.addData(remoteData)
    }
}