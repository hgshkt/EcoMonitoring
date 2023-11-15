package data.storage.materials

import data.storage.materials.model.RemoteStorageMaterial

interface MaterialRemoteStorage {
    fun getAll(): List<RemoteStorageMaterial>
}