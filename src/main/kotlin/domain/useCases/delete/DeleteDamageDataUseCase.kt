package domain.useCases.delete

import domain.data.repository.damageData.DamageDataRemoteRepository

class DeleteDamageDataUseCase(
    private val repository: DamageDataRemoteRepository
) {
    fun execute() {
        repository.delete()
    }
}