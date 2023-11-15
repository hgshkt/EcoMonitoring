package data.storage.remote.materials

import data.storage.remote.materials.model.RemoteMaterial
import domain.model.Material

interface MaterialRemoteStorage {
    fun getAll(): List<RemoteMaterial>
}