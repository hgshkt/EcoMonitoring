package data.obtained

class SimpleDamageCalculator {
    fun calcDamage(
        mi: Double,
        P: Int,
        Ai: Double,
        Kt: Double,
        Kzi: Double
    ): Double {
        return mi * 1.1 * P * Ai * Kt * Kzi
    }
}