package data.obtained

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