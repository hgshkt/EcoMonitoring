package data.obtained.calculatorImpls

import data.mappers.domain.pollution.toRemote
import data.mappers.domain.taxRate.toRemote
import data.obtained.DLAtmosphereTaxCalculator
import data.storage.remote.taxRate.TaxRateRemoteStorage
import data.storage.remote.taxRate.model.RemoteTaxRate
import domain.data.obtained.calculators.TaxCalculator
import domain.data.repository.taxRate.remote.TaxRateRemoteRepository
import domain.model.Pollution

class TaxCalculatorImpl(
    private val atmosphereTaxCalculator: DLAtmosphereTaxCalculator,
    private val taxRatesRepository: TaxRateRemoteStorage
) : TaxCalculator {
    override fun calcAtmosphereTax(pollution: Pollution): Double {
        val taxRates = taxRatesRepository.getAll()

        val remotePollution = pollution.toRemote()

        val tax = atmosphereTaxCalculator.calc(
            pollution.materialAmount,
            taxRate = taxRates.find { taxRate ->
                taxRate.materialId == remotePollution.materialId
            }?.value ?: -1.0
        )
        pollution.tax = tax
        return tax
    }
}