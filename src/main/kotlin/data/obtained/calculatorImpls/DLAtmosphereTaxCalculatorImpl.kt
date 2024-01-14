package data.obtained.calculatorImpls

import data.obtained.DLAtmosphereTaxCalculator

class DLAtmosphereTaxCalculatorImpl : DLAtmosphereTaxCalculator {
    override fun calc(materialAmount: Double, taxRate: Double): Double {
        return materialAmount * taxRate
    }
}