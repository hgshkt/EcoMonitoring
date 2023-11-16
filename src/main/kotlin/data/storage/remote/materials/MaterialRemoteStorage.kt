package data.storage.remote.materials

import data.storage.remote.materials.model.RemoteMaterial

interface MaterialRemoteStorage {
    fun getAll(): List<RemoteMaterial>

    fun getById(id: Int): RemoteMaterial?
}