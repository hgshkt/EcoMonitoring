package data.obtained.calculatorImpls

import domain.data.obtained.calculators.MassCalculator
import domain.model.Material
import domain.model.YearConcentration

class MassCalculatorImpl : MassCalculator {
    override fun calc(
        material: Material,
        concentration: Double,
        qv: Double,
        T: Int
    ): Double {
        return 0.0000036 * (concentration - material.gdv) * qv * T
    }
}