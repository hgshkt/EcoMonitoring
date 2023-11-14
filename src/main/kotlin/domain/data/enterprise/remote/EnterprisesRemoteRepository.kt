package domain.data.enterprise.remote

import domain.model.Enterprise

interface EnterprisesRemoteRepository {
    fun getData(): MutableList<Enterprise>
}