package data.storage.remote.materials

import data.storage.remote.materials.model.RemoteMaterial

interface MaterialRemoteStorage {
    fun getAll(): List<RemoteMaterial>

    fun getById(id: Int): RemoteMaterial?

    fun add(materials: List<RemoteMaterial>)

    fun getByName(name: String): RemoteMaterial?

    fun deleteById(id: Int)

    fun deleteAll()
    fun update(material: RemoteMaterial)
}