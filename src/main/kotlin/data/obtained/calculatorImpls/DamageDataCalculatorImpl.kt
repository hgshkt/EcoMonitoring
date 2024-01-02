package data.obtained.calculatorImpls

import domain.data.repository.damageData.DamageDataCalculator
import domain.model.DamageData

class DamageDataCalculatorImpl: DamageDataCalculator {
    override fun calculate(data: DamageData): DamageData {
        return DamageData(
            population = data.population,
            knas = data.knas(),
            kf = data.kf,
            kt = data.kt(),
            P = data.P
        )
    }

    private fun DamageData.kt(): Double {
        return knas * kf
    }

    private fun DamageData.knas(): Double {
        return if (population < 100000) 1.00
        else if (population in 100000..250000) 1.2
        else if (population in 250000..500000) 1.35
        else if (population in 500000..1000000) 1.55
        else 1.8
    }
}