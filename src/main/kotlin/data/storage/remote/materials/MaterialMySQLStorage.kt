package data.storage.remote.materials

import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.model.RemoteEnterprise
import data.storage.remote.materials.model.RemoteMaterial
import domain.model.Material
import java.sql.*

class MaterialMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : MaterialRemoteStorage {

    private val getAllQuery = "SELECT * FROM materials"
    private val getByIdQuery = "SELECT * FROM materials WHERE id = ?"

    private val columnIdName = "id"
    private val columnNameName = "material_name"
    private val columnGdkName = "gdk"
    private val columnDangerClassName = "danger_class"

    override fun getAll(): List<RemoteMaterial> {

        val materials = mutableListOf<RemoteMaterial>()

        try {
            val connection: Connection = DriverManager.getConnection(
                connectionData.url,
                connectionData.user,
                connectionData.password
            )

            val statement = connection.createStatement()
            val resultSet: ResultSet = statement.executeQuery(getAllQuery)

            while (resultSet.next()) {
                materials.add(
                    element = getMaterialFromResultSet(resultSet)
                )
            }
            resultSet.close()
            statement.close()
            connection.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return materials
    }

    override fun getById(id: Int): RemoteMaterial? {
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

            var material: RemoteMaterial? = null

            if (resultSet.next()) {
                material = getMaterialFromResultSet(resultSet)
            }

            resultSet.close()
            preparedStatement.close()
            connection.close()

            return material

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }

    private fun getMaterialFromResultSet(
        resultSet: ResultSet
    ): RemoteMaterial {
        val id = resultSet.getInt(columnIdName)
        val name = resultSet.getString(columnNameName)
        val gdk = resultSet.getDouble(columnGdkName)
        val dangerClass = resultSet.getInt(columnDangerClassName)

        return RemoteMaterial(
            id = id,
            name = name,
            gdk = gdk,
            dangerClass = dangerClass
        )
    }
}