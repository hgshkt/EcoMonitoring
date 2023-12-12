package data.mappers.domain.yearConcentration

import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import domain.model.YearConcentration

fun YearConcentration.toRemote() = RemoteYearConcentration(
    id = id,
    materialId = -1,
    value = value,
    year = year,
    carcinogenicRisk = carcinogenicRisk,
    nonCarcinogenicRisk = nonCarcinogenicRisk,
    organ = organ,
    carcinogenicRiskLevel = carcinogenicRiskLevel,
    nonCarcinogenicRiskLevel = nonCarcinogenicRiskLevel
)