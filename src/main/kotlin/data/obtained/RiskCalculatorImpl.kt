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

            concentration.calcCarcinogenicRisk()
            concentration.calcCarcinogenicRiskLevel()

            concentration.calcNonCarcinogenicRisk(material)
            concentration.calcNonCarcinogenicRiskLevel()
        }
        return concentrations
    }

    private fun YearConcentration.calcCarcinogenicRisk() {
        carcinogenicRisk = (((value * Tout * Vout) + (value * Tin * Vin)) * EF * ED) /
                (BW * AT * 365) * population
    }

    private fun YearConcentration.calcCarcinogenicRiskLevel() {
        RiskLevel.entries.forEach { riskLevel ->
            if (riskLevel.condition(value)) {
                carcinogenicRiskLevel = riskLevel.toString()
                return
            }
        }
    }

    private fun YearConcentration.calcNonCarcinogenicRisk(
        material: Material
    ) = value * material.RfC

    private fun YearConcentration.calcNonCarcinogenicRiskLevel() {
        RiskLevel.entries.forEach { riskLevel ->
            if (riskLevel.condition(value)) {
                nonCarcinogenicRiskLevel = riskLevel.toString()
                return
            }
        }
    }
}