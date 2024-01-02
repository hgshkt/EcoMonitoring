package domain.useCases.get

import domain.data.repository.damageData.DamageDataRemoteRepository
import domain.model.DamageData

class GetDamageDataFromRemoteRepositoryUseCase(
    private val remoteRepository: DamageDataRemoteRepository,
){
    fun execute(): DamageData {
        return remoteRepository.get()
    }
}