package data.obtained.calculatorImpls

import domain.data.obtained.calculators.MaterialCalculator
import domain.model.Material
import domain.model.YearConcentration

class MaterialCalculatorImpl : MaterialCalculator {
    override fun calculate(material: Material, concentration: YearConcentration) {
        with(material) {
            Ai = 1 / gdk
            Kzi = concentration.value / gdk
        }
    }
}