package domain.data.obtained.calculators

import domain.model.Material
import domain.model.YearConcentration

interface MaterialCalculator {
    fun calculate(material: Material, concentration: YearConcentration)
}