package data.storage.remote.materials

import data.storage.remote.materials.model.RemoteStorageMaterial

interface MaterialRemoteStorage {
    fun getAll(): List<RemoteStorageMaterial>
}