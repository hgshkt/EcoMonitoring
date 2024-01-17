package domain.useCases.loadFromExcel

import domain.data.obtained.calculators.DamageCalculator
import domain.data.repository.damageData.DamageDataCalculator
import domain.data.repository.damageData.DamageDataExcelRepository
import domain.data.repository.damageData.DamageDataRemoteRepository
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.pollution.remote.PollutionsRemoteRepository

class LoadDamageDataFromExcelUseCase(
    private val excelRepository: DamageDataExcelRepository,
    private val remoteRepository: DamageDataRemoteRepository,
    private val calculator: DamageDataCalculator,
    private val pollutionRepository: PollutionsRemoteRepository,
    private val damageCalculator: DamageCalculator,
    private val materialRepository: MaterialsRemoteRepository
) {
    fun execute(fileName: String) {
        val excelData = excelRepository.load(fileName)
        val remoteData = calculator.calculate(excelData)
        remoteRepository.put(remoteData)

        val materials = materialRepository.getData().materials

        pollutionRepository.getAll().map { pollution->

            val material = materials.find { it.name == pollution.materialName }!!

            damageCalculator.calcDamage(
                pollution = pollution,
                material = material,
                damageData = remoteData
            )
            pollutionRepository.updateDamage(pollution)
        }
    }
}