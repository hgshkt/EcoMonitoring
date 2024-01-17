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
    private val columnRfCName = "RfC"
    private val columnOrganName = "organ"
    private val columnGdvName = "gdv"
    private val columnMassEmissionsName = "mass_emissions"
    private val columnAiName = "Ai"
    private val columnKziName = "Kzi"

    private val getAllQuery = "SELECT * FROM $tableName"
    private val getByIdQuery = "SELECT * FROM $tableName WHERE $columnIdName = ?"
    private val getByNameQuery = "SELECT * FROM $tableName WHERE $columnNameName = ?"
    private val insertQuery = "INSERT INTO $tableName VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
    private val deleteQuery = "DELETE FROM $tableName WHERE $columnIdName = ?"
    private val deleteAllQuery = "DELETE FROM $tableName"
    private val updateQuery = "UPDATE $tableName SET $columnNameName = ?, " +
            "$columnGdkName = ?, $columnDangerClassName = ?, $columnRfCName = ?, $columnOrganName = ?, " +
            "$columnGdvName = ?, $columnMassEmissionsName = ?, $columnAiName = ?, $columnKziName =? " +
            "WHERE $columnIdName = ?"

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
                    element = RemoteMaterial(
                        id = resultSet.getInt(columnIdName),
                        name = resultSet.getString(columnNameName),
                        gdk = resultSet.getDouble(columnGdkName),
                        dangerClass = resultSet.getInt(columnDangerClassName),
                        RfC = resultSet.getDouble(columnRfCName),
                        organ = resultSet.getString(columnOrganName),
                        gdv = resultSet.getInt(columnGdvName),
                        massEmissions = resultSet.getInt(columnMassEmissionsName),
                        Ai = resultSet.getDouble(columnAiName),
                        Kzi = resultSet.getDouble(columnKziName)
                    )
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

    override fun update(material: RemoteMaterial) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).prepareStatement(updateQuery).use {
            with(it) {
                setString(1, material.name)
                setDouble(2, material.gdk)
                setInt(3, material.dangerClass)
                setDouble(4, material.RfC)
                setString(5, material.organ)
                setInt(6, material.gdv)
                setInt(7, material.massEmissions)
                setDouble(8, material.Ai)
                setDouble(9, material.Kzi)

                setInt(10, material.id)

                executeUpdate()
            }
        }
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

            val resultSet: ResultSet = preparedStatement.executeQuery()

            var material: RemoteMaterial? = null

            if (resultSet.next()) {
                material = RemoteMaterial(
                    id = resultSet.getInt(columnIdName),
                    name = resultSet.getString(columnNameName),
                    gdk = resultSet.getDouble(columnGdkName),
                    dangerClass = resultSet.getInt(columnDangerClassName),
                    RfC = resultSet.getDouble(columnRfCName),
                    organ = resultSet.getString(columnOrganName),
                    gdv = resultSet.getInt(columnGdvName),
                    massEmissions = resultSet.getInt(columnMassEmissionsName),
                    Ai = resultSet.getDouble(columnAiName),
                    Kzi = resultSet.getDouble(columnKziName)
                )
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
                    preparedStatement.setDouble(5, material.RfC)
                    preparedStatement.setString(6, material.organ)
                    preparedStatement.setInt(7, material.gdv)
                    preparedStatement.setInt(8, material.massEmissions)
                    preparedStatement.setDouble(9, material.Ai)
                    preparedStatement.setDouble(10, material.Kzi)

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

            val resultSet: ResultSet = preparedStatement.executeQuery()

            var material: RemoteMaterial? = null

            if (resultSet.next()) {
                material = RemoteMaterial(
                    id = resultSet.getInt(columnIdName),
                    name = resultSet.getString(columnNameName),
                    gdk = resultSet.getDouble(columnGdkName),
                    dangerClass = resultSet.getInt(columnDangerClassName),
                    RfC = resultSet.getDouble(columnRfCName),
                    organ = resultSet.getString(columnOrganName),
                    gdv = resultSet.getInt(columnGdvName),
                    massEmissions = resultSet.getInt(columnMassEmissionsName),
                    Ai = resultSet.getDouble(columnAiName),
                    Kzi = resultSet.getDouble(columnKziName)
                )
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

    override fun deleteAll() {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.createStatement().use { statement ->
                statement.executeUpdate(deleteAllQuery)
            }
        }
    }
}