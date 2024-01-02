package domain.useCases.calculation

import domain.data.obtained.calculators.MassCalculator
import domain.model.Material

class CalculateMassUseCase(
    private val calculator: MassCalculator
) {
    fun execute(
        material: Material,
        concentration: Double,
        qv: Double,
        T: Int
    ): Double {
        return calculator.calc(
            material = material,
            concentration = concentration,
            qv = qv,
            T = T
        )
    }
}