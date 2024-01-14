package data.obtained.calculatorImpls

import data.mappers.domain.pollution.toRemote
import data.obtained.DLAtmosphereTaxCalculator
import data.storage.remote.taxRate.model.RemoteTaxRate
import domain.data.obtained.calculators.TaxCalculator
import domain.model.Pollution

class TaxCalculatorImpl(
    private val taxRates: List<RemoteTaxRate>,
    private val atmosphereTaxCalculator: DLAtmosphereTaxCalculator
) : TaxCalculator {
    override fun calcAtmosphereTax(pollution: Pollution): Double {
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