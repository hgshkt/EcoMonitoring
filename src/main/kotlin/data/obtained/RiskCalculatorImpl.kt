package data.obtained

import domain.data.obtained.calculators.RiskCalculator
import domain.model.Material
import domain.model.DayConcentration

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
        material: Material,
        concentration: DayConcentration
    ): DayConcentration {


        concentration.calcCarcinogenicRisk()
        concentration.calcCarcinogenicRiskLevel()

        concentration.calcNonCarcinogenicRisk(material)
        concentration.calcNonCarcinogenicRiskLevel()

        return concentration
    }

    private fun DayConcentration.calcCarcinogenicRisk() {
        carcinogenicRisk = (((value * Tout * Vout) + (value * Tin * Vin)) * EF * ED) * population /
                (BW * AT * 365)
    }

    private fun DayConcentration.calcCarcinogenicRiskLevel() {
        RiskLevel.entries.forEach { riskLevel ->
            if (riskLevel.condition(value)) {
                carcinogenicRiskLevel = riskLevel.toString()
                return
            }
        }
    }

    private fun DayConcentration.calcNonCarcinogenicRisk(
        material: Material
    ) {
        nonCarcinogenicRisk = value * material.RfC
    }

    private fun DayConcentration.calcNonCarcinogenicRiskLevel() {
        RiskLevel.entries.forEach { riskLevel ->
            if (riskLevel.condition(value)) {
                nonCarcinogenicRiskLevel = riskLevel.toString()
                return
            }
        }
    }
}