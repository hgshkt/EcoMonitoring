package data.obtained

import data.obtained.EcoData.POPULATION
import domain.data.obtained.calculators.DamageCalculator
import domain.model.Material
import domain.model.Pollution
import domain.model.RiskLevel
import domain.model.YearConcentration

class DamagesCalculatorImpl : DamageCalculator {

    private val qV: Double = 1.0

    private val T: Double = 1.0

    /**
     * розмір  мінімальної  заробітної  плати
     */
    private val P = 4723

    /** коефіцієнт,      що      враховує      територіальні
     * соціально-екологічні особливості
     */
    private val Kt: Double
        get() {
            return Knas * Kf
        }

    /**
     * коефіцієнт,  що залежить  від  чисельності  жителів
     * населеного пункту
     */
    private val Knas: Double
        get() {
            return if (POPULATION in 0..100000) {
                1.0
            } else if (POPULATION in 100000..250000) {
                1.2
            } else if (POPULATION in 250000..500000) {
                1.35
            } else if (POPULATION in 500000..1000000) {
                1.55
            } else if (POPULATION > 1000000) {
                1.8
            } else 0.0
        }

    /**
     * коефіцієнт,  що  враховує  народногосподарське значення
     * населеного пункту
     */
    private val Kf = 1.25

    /** маса   i-тої  забруднюючої  речовини,  що  викинута  в
     * атмосферне повітря наднормативно, т
     */
    private fun mi(concentration: Double, gdk: Double): Double {
        val poBi = concentration
        val poBt = gdk
        return 0.0000036 * (poBi - poBt) * qV * T
    }

    override fun calcDamage(
        material: Material,
        pollution: Pollution
    ): Double {
        val Ai = Ai(material)
        val Kzi = Kzi(pollution, material)
        return mi(pollution.concentration, material.gdk) * 1.1 * P * Ai * Kt * Kzi
    }
}

/** коефіцієнт,   що   залежить   від  рівня  забруднення
 * атмосферного  повітря   населеного   пункту   i-тою   забруднюючою речовиною.
 */
private fun Kzi(
    pollution: Pollution,
    material: Material
) = pollution.concentration / material.gdk


/** безрозмірний  показник  відносної  небезпечності  i-тої
 * забруднюючої речовини
 */
private fun Ai(material: Material) = 1 / material.gdk


val pollution = Pollution(
    enterpriseName = "enterprise 1",
    materialName = "material 1",
    year = 2022,
    materialAmount = 2657.0,
    concentration = 0.00109,
    carcinogenicRisk = 0.00013,
    carcinogenicRiskLevel = RiskLevel.LOW.levelName,
    nonCarcinogenicRisk = 0.0000436,
    nonCarcinogenicRiskLevel = RiskLevel.MEDIUM.levelName
)

val material = Material(
    id = 1,
    name = "material 1",
    gdk = 0.04,
    dangerClass = 3,
    RfC = 0.04,
    organ = "organ 1",
    gdv = 325,
    massEmissions = 1000
)

fun main() {
    val calculator = DamagesCalculatorImpl()

    val damage = calculator.calcDamage(
        material = material,
        pollution = pollution
    )

    println(damage)
}