package data.materials.remote.storage

import data.materials.remote.storage.model.RemoteStorageMaterial

interface MaterialRemoteStorage {
    fun getAll(): List<RemoteStorageMaterial>
}