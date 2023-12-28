package domain.model

import java.lang.IllegalArgumentException

enum class RiskLevel(val levelName: String) {
    HIGH("HIGH") {
        override fun condition(concentration: Double): Boolean {
            return concentration >= 0.001
        }

    },
    MEDIUM("MEDIUM") {
        override fun condition(concentration: Double): Boolean {
            return concentration < 0.001 && concentration >= 0.0001
        }

    },
    LOW("LOW") {
        override fun condition(concentration: Double): Boolean {
            return concentration < 0.0001 && concentration >= 0.000001
        }

    },
    MINIMUM("MINIMUM") {
        override fun condition(concentration: Double): Boolean {
            return concentration < 0.000001
        }

    },
    UNDEFINED("UNDEFINED") {
        override fun condition(concentration: Double) = true

    };

    abstract fun condition(concentration: Double): Boolean

    companion object {
        fun find(concentration: Double): RiskLevel {
            if(concentration == -1.0) return UNDEFINED

            entries.forEach { riskLevel ->
                if (riskLevel.condition(concentration)) return riskLevel
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