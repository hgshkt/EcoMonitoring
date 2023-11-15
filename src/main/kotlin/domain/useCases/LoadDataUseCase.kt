package domain.useCases

import domain.data.repository.enterprise.remote.EnterprisesRemoteRepository
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.pollution.remote.PollutionsRemoteRepository

fun loadData(
    pollutionsRepository: PollutionsRemoteRepository,
    materialsRepository: MaterialsRemoteRepository,
    enterprisesRepository: EnterprisesRemoteRepository
) = LoadedData(
    pollutions = pollutionsRepository.getData(),
    materials = materialsRepository.getData(),
    enterprises = enterprisesRepository.getData()
)
