package domain.useCases.loadFromExcel

import data.mappers.domain.pollution.toRemote
import data.storage.remote.pollutions.model.RemotePollution
import domain.data.obtained.calculators.TaxCalculator
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.data.repository.taxRate.excel.ExcelTaxRateRepository
import domain.data.repository.taxRate.remote.TaxRateRemoteRepository
import domain.model.Pollution

class LoadTaxRateFromExcelUseCase(
    private val taxRateExcelRepository: ExcelTaxRateRepository,
    private val taxRateRemoteRepository: TaxRateRemoteRepository,
    private val taxCalculator: TaxCalculator,
    pollutionsRepository: PollutionsRemoteRepository
) {

    private val pollutions: MutableList<Pollution> = pollutionsRepository.getAll()

    fun execute(fileName: String) {

        val taxRates = taxRateExcelRepository.getData(fileName)
        taxRates.forEach {taxRate ->
            taxRateRemoteRepository.add(taxRate)
            val pollution = pollutions.find {pollution ->
                pollution.materialName == taxRate.materialName
            }
            taxCalculator.calcAtmosphereTax(pollution!!)
        }
    }
}