package domain.useCases

import domain.data.enterprise.remote.EnterprisesRemoteRepository
import domain.data.material.remote.MaterialsRemoteRepository
import domain.data.pollution.remote.PollutionsRemoteRepository
import domain.model.data.remote.LoadedData

fun loadData(
    pollutionsRepository: PollutionsRemoteRepository,
    materialsRepository: MaterialsRemoteRepository,
    enterprisesRepository: EnterprisesRemoteRepository
) = LoadedData(
    pollutions = pollutionsRepository.getData(),
    materials = materialsRepository.getData(),
    enterprises = enterprisesRepository.getData()
)
