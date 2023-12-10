package data.obtained

import domain.data.obtained.calculators.RiskCalculator
import domain.model.Material
import domain.model.YearConcentration

class RiskCalculatorImpl : RiskCalculator {

    private val Tout = 8
    private val Tin = 16
    private val Vout = 1.4
    private val Vin = 0.63
    private val EF = 350
    private val ED = 30
    private val BW = 70
    private val AT = 70
    private val population = 3096500

    override fun calculateRisk(
        materials: MutableList<Material>,
        concentrations: MutableList<YearConcentration>
    ): MutableList<YearConcentration> {
        for (concentration in concentrations) {
            val material = materials.find {
                concentration.materialId == it.id
            }!!
            concentration.carcinogenicRisk = calcCarcinogenicRisk(concentration)
            concentration.nonCarcinogenicRisk = calcNonCarcinogenicRisk(concentration, material)
        }
        return concentrations
    }

    private fun calcCarcinogenicRisk(
        concentration: YearConcentration
    ) = (((concentration.value * Tout * Vout) + (concentration.value * Tin * Vin)) * EF * ED) /
            (BW * AT * 365) * population


    private fun calcNonCarcinogenicRisk(
        concentration: YearConcentration,
        material: Material
    ) = concentration.value * material.RfC
}