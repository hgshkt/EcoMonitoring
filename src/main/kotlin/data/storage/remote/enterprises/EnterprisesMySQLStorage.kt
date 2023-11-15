package data.storage.remote.enterprises

import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.model.RemoteEnterprise
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class EnterprisesMySQLStorage(
    private val connectionData: DatabaseConnectionData
): EnterpriseRemoteStorage {

    private val getAllQuery = "SELECT * FROM enterprises"

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
                val id = resultSet.getInt(columnIdName)
                val name = resultSet.getString(columnNameName)
                val activity = resultSet.getString(columnActivityName)
                val belonging = resultSet.getString(columnBelongingName)
                val location = resultSet.getString(columnLocationName)

                val enterprise = RemoteEnterprise(
                    id = id,
                    name = name,
                    activity = activity,
                    belonging = belonging,
                    location = location
                )
                enterprises.add(enterprise)
            }

            resultSet.close()
            statement.close()
            connection.close()

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return enterprises
    }
}