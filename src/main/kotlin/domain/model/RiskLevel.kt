package domain.model

import java.lang.IllegalArgumentException

enum class RiskLevel(val levelName: String) {
    HIGH("HIGH") {
        override fun condition(risk: Double): Boolean {
            return risk >= 0.001
        }

    },
    MEDIUM("MEDIUM") {
        override fun condition(risk: Double): Boolean {
            return risk < 0.001 && risk >= 0.0001
        }

    },
    LOW("LOW") {
        override fun condition(risk: Double): Boolean {
            return risk < 0.0001 && risk >= 0.000001
        }

    },
    MINIMUM("MINIMUM") {
        override fun condition(risk: Double): Boolean {
            return risk < 0.000001
        }

    },
    UNDEFINED("UNDEFINED") {
        override fun condition(risk: Double) = true

    };

    abstract fun condition(risk: Double): Boolean

    companion object {
        fun find(risk: Double): RiskLevel {
            if(risk == -1.0) return UNDEFINED

            entries.forEach { riskLevel ->
                if (riskLevel.condition(risk)) return riskLevel
            }
            return UNDEFINED
        }

        fun byName(levelName: String): RiskLevel {
            entries.forEach {
                if (it.levelName == levelName) return it
            }
            throw IllegalArgumentException("$levelName level is not set in the list of risk levels")
        }
    }
}