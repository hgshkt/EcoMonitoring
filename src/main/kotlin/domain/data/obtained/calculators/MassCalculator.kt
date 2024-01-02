package domain.data.obtained.calculators

import domain.model.Material
import domain.model.YearConcentration

interface MassCalculator {
    fun calc(
        material: Material,
        concentration: Double,
        qv: Double,
        T: Int
    ): Double
}