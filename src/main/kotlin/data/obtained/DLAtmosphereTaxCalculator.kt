package data.obtained

interface DLAtmosphereTaxCalculator {

    fun calc(materialAmount: Double, taxRate: Double): Double
}