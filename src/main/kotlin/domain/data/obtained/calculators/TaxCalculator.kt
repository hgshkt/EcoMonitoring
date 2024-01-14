package domain.data.obtained.calculators

import domain.model.Pollution

interface TaxCalculator {

    // повертає значення податку та змінює його всередені моделі викиду
    fun calcAtmosphereTax(pollution: Pollution): Double
}