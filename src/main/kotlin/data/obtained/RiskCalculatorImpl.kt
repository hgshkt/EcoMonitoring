package data.obtained

import domain.data.obtained.calculators.RiskCalculator
import domain.model.Material
import domain.model.Pollution
import domain.model.RiskLevel

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
        pollution: Pollution
    ): Pollution {
        pollution.calcCarcinogenicRisk()
        pollution.calcCarcinogenicRiskLevel()

        pollution.calcNonCarcinogenicRisk(material)
        pollution.calcNonCarcinogenicRiskLevel()

        return pollution
    }

    private fun Pollution.calcCarcinogenicRisk() {
        carcinogenicRisk =
            if(concentration == -1.0) -1.0
        else (((concentration * Tout * Vout) + (concentration * Tin * Vin)) * EF * ED)  /
                (BW * AT * 365)
    }

    private fun Pollution.calcNonCarcinogenicRisk(
        material: Material
    ) {
        nonCarcinogenicRisk =
            if (concentration == -1.0) -1.0
        else
            concentration * material.RfC
    }
}

private fun Pollution.calcNonCarcinogenicRiskLevel() {
    carcinogenicRiskLevel = RiskLevel.find(carcinogenicRisk).levelName
}

private fun Pollution.calcCarcinogenicRiskLevel() {
    nonCarcinogenicRiskLevel = RiskLevel.find(nonCarcinogenicRisk).levelName
}
