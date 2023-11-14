package domain.data.material.remote

import domain.model.Material
import domain.model.Pollution

interface MaterialsRemoteRepository {
    fun getData(): MutableList<Material>
}