package domain.useCases

import data.loadEnterprises
import data.loadMaterials
import data.loadPollutions
import domain.data.model.LoadedData

fun loadData(
    materialsExcelFileName: String,
    pollutionsExcelFileName: String,
    enterprisesExcelFileName: String
) = LoadedData(
    materials = loadMaterials(materialsExcelFileName),
    pollutions = loadPollutions(pollutionsExcelFileName),
    enterprises = loadEnterprises(enterprisesExcelFileName)
)