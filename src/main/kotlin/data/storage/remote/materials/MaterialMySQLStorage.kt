package data.storage.remote.materials

import data.storage.DatabaseConnectionData
import data.storage.remote.materials.model.RemoteMaterial
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class MaterialMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : MaterialRemoteStorage {

    private val getAllQuery = "SELECT * FROM materials"

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
                val id = resultSet.getInt(columnIdName)
                val name = resultSet.getString(columnNameName)
                val gdk = resultSet.getDouble(columnGdkName)
                val dangerClass = resultSet.getInt(columnDangerClassName)

                val material = RemoteMaterial(
                    id = id,
                    name = name,
                    gdk = gdk,
                    dangerClass = dangerClass
                )
                materials.add(material)
            }
            resultSet.close()
            statement.close()
            connection.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return materials
    }
}