package data.obtained

import domain.data.obtained.calculators.DamageCalculator
import domain.model.Material
import domain.model.YearConcentration

class DamagesCalculatorImpl: DamageCalculator {

    private val poBi: Double = 1.0

    private val poBt: Double = 1.0

    private val qV: Double = 1.0

    private val T: Double = 1.0

    /**
     * розмір  мінімальної  заробітної  плати
     */
    private val P = 0

    /** безрозмірний  показник  відносної  небезпечності  i-тої
     * забруднюючої речовини
     */
    private var Ai = 0.0

    /** коефіцієнт,      що      враховує      територіальні
     * соціально-екологічні особливості
     */
    private var Kt = 0.0

    /**
     * коефіцієнт,  що залежить  від  чисельності  жителів
     * населеного пункту
     */
    private val Knas = 1.55

    /**
     * коефіцієнт,  що  враховує  народногосподарське значення
     * населеного пункту
     */
    private val Kf = 1.25

    /** коефіцієнт,   що   залежить   від  рівня  забруднення
     * атмосферного  повітря   населеного   пункту   i-тою   забруднюючою речовиною.
     */
    private var Kzi = 0.0

    /**
     * середньорічна  концентрація  i-тої забруднюючої
     * речовини за даними прямих інструментальних вимірів на стаціонарних
     * постах за попередній рік, мг/куб.м
     */
    private val po = 0


    override fun calcDamage(
        material: Material,
        concentration: YearConcentration
    ): Double {
        Ai = 1 / material.gdk
        Kt = Knas * Kf
        Kzi = concentration.value / material.gdk
        return mi() * 1.1 * P * Ai * Kt * Kzi
    }

    /** маса   i-тої  забруднюючої  речовини,  що  викинута  в
     * атмосферне повітря наднормативно, т
     */
    private fun mi(): Double {
        return 0.0000036 * (poBi - poBt)*qV*T
    }
}