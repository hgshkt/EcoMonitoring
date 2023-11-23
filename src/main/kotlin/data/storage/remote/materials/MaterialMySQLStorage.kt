package data.storage.remote.materials

import data.storage.DatabaseConnectionData
import data.storage.remote.materials.model.RemoteMaterial
import java.sql.*

class MaterialMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : MaterialRemoteStorage {

    private val tableName = "materials"


    private val columnIdName = "id"
    private val columnNameName = "material_name"
    private val columnGdkName = "gdk"
    private val columnDangerClassName = "danger_class"

    private val getAllQuery = "SELECT * FROM $tableName"
    private val getByIdQuery = "SELECT * FROM $tableName WHERE $columnIdName = ?"
    private val getByNameQuery = "SELECT * FROM $tableName WHERE $columnNameName = ?"
    private val insertQuery = "INSERT INTO $tableName VALUES (?, ?, ?, ?)"
    private val deleteQuery = "DELETE FROM $tableName WHERE $columnIdName = ?"


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

    override fun add(materials: List<RemoteMaterial>) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(insertQuery).use { preparedStatement ->

                for (material in materials) {

                    print(material.name.length)

                    preparedStatement.setInt(1, material.id)
                    preparedStatement.setString(2, material.name)
                    preparedStatement.setDouble(3, material.gdk)
                    preparedStatement.setInt(4, material.dangerClass)

                    preparedStatement.executeUpdate()
                }
            }
        }
    }

    override fun getByName(name: String): RemoteMaterial? {
        try {
            val connection: Connection = DriverManager.getConnection(
                connectionData.url,
                connectionData.user,
                connectionData.password
            )

            val preparedStatement: PreparedStatement =
                connection.prepareStatement(getByNameQuery)

            preparedStatement.setString(1, name)

            val resultSet: ResultSet = preparedStatement.executeQuery(getByNameQuery)

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

    override fun deleteById(id: Int) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(deleteQuery).use { prepareStatement ->
                prepareStatement.setString(1, id.toString())
                prepareStatement.executeUpdate()
            }
        }
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