package data.obtained

import domain.model.Material
import domain.model.DayConcentration

enum class RiskLevel {
    HIGH {
        override fun condition(value: Double): Boolean {
            return value > 0.001
        }

        override fun toString() = "High"
    },
    MEDIUM {
        override fun condition(value: Double): Boolean {
            return value <= 0.001 && value > 0.0001
        }

        override fun toString() = "Medium"
    },
    LOW {
        override fun condition(value: Double): Boolean {
            return value in 0.000001..0.0001
        }

        override fun toString() = "Low"
    },
    MINIMUM {
        override fun condition(value: Double): Boolean {
            return value < 0.000001
        }

        override fun toString() = "Minimum"
    };

    abstract fun condition(value: Double): Boolean
}

fun main() {
    val calculator = RiskCalculatorImpl()
    val o = calculator.calculateRisk(
        material = Material(
            id = 55,
            name = "Бенз(а)пірен",
            gdk = 0.000001,
            dangerClass = 1,
            RfC = 0.0,
            organ = ""
        ),
        concentration = DayConcentration(
            id = 200,
            materialName = "Бенз(а)пірен",
            value =  0.00000095,
            year = 1950
        )
    )
    print(o)
}