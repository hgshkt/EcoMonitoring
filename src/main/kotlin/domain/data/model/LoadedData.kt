package domain.data.model

import domain.model.Enterprise
import domain.model.Material
import domain.model.Pollution

data class LoadedData(
    val enterprises: MutableList<Enterprise>,
    val materials: MutableList<Material>,
    val pollutions: MutableList<Pollution>
)
