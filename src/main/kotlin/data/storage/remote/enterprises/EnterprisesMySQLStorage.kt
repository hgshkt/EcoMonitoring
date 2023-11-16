package data.storage.remote.enterprises

import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.model.RemoteEnterprise
import data.storage.remote.materials.model.RemoteMaterial
import java.sql.*

class EnterprisesMySQLStorage(
    private val connectionData: DatabaseConnectionData
): EnterpriseRemoteStorage {

    private val getAllQuery = "SELECT * FROM enterprises"
    private val getByIdQuery = "SELECT * FROM enterprises WHERE id = ?"

    private val columnIdName = "id"
    private val columnNameName = "enterprise_name"
    private val columnActivityName = "activity"
    private val columnBelongingName = "belonging"
    private val columnLocationName = "location"

    override fun getAll(): List<RemoteEnterprise> {
        val enterprises = mutableListOf<RemoteEnterprise>()

        try {
            val connection: Connection = DriverManager.getConnection(
                connectionData.url,
                connectionData.user,
                connectionData.password
            )

            val statement = connection.createStatement()
            val resultSet: ResultSet = statement.executeQuery(getAllQuery)

            while (resultSet.next()) {
                enterprises.add(
                    element = getEnterpriseFromResultSet(resultSet)
                )
            }

            resultSet.close()
            statement.close()
            connection.close()

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return enterprises
    }

    override fun getById(id: Int): RemoteEnterprise? {
        try {
            val connection: Connection = DriverManager.getConnection(
                connectionData.url,
                connectionData.user,
                connectionData.password
            )

            val preparedStatement: PreparedStatement =
                connection.prepareStatement(getByIdQuery)

            preparedStatement.setInt(1, id)

            val resultSet: ResultSet = preparedStatement.executeQuery(getByIdQuery)

            var enterprise: RemoteEnterprise? = null

            if (resultSet.next()) {
                enterprise = getEnterpriseFromResultSet(resultSet)
            }

            resultSet.close()
            preparedStatement.close()
            connection.close()

            return enterprise

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    private fun getEnterpriseFromResultSet(
        resultSet: ResultSet
    ): RemoteEnterprise {
        val id = resultSet.getInt(columnIdName)
        val name = resultSet.getString(columnNameName)
        val activity = resultSet.getString(columnActivityName)
        val belonging = resultSet.getString(columnBelongingName)
        val location = resultSet.getString(columnLocationName)

        return RemoteEnterprise(
            id = id,
            name = name,
            activity = activity,
            belonging = belonging,
            location = location
        )
    }
}