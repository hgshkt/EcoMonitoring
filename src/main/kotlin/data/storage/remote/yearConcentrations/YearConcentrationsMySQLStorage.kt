package data.storage.remote.yearConcentrations

import data.storage.DatabaseConnectionData
import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import java.sql.*

class YearConcentrationsMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : YearConcentrationsRemoteStorage {

    private val tableName = "year_concentration"

    private val columnIdName = "id"
    private val columnYearName = "year"
    private val columnValueName = "value"
    private val columnMaterialIdName = "material_id"
    private val columnCarcinogenicRiskName = "carcinogenic_risk"
    private val columnNonCarcinogenicRiskName = "non_carcinogenic_risk"

    private val getAllQuery = "SELECT * FROM $tableName"
    private val getByIdQuery = "SELECT * FROM $tableName WHERE $columnIdName = ?"
    private val insertQuery = "INSERT INTO $tableName VALUES (?, ?, ?, ?, ?, ?)"
    private val deleteQuery = "DELETE FROM $tableName WHERE $columnIdName = ?"
    private val deleteAllQuery = "DELETE FROM $tableName"

    override fun getAll(): List<RemoteYearConcentration> {
        val concentrations = mutableListOf<RemoteYearConcentration>()

        try {
            val connection: Connection = DriverManager.getConnection(
                connectionData.url,
                connectionData.user,
                connectionData.password
            )

            val statement = connection.createStatement()
            val resultSet: ResultSet = statement.executeQuery(getAllQuery)

            while (resultSet.next()) {
                val concentration = RemoteYearConcentration(
                    id = resultSet.getInt(columnIdName),
                    materialId = resultSet.getInt(columnMaterialIdName),
                    value = resultSet.getDouble(columnValueName),
                    year = resultSet.getInt(columnYearName),
                    carcinogenicRisk = resultSet.getDouble(columnCarcinogenicRiskName),
                    nonCarcinogenicRisk = resultSet.getDouble(columnNonCarcinogenicRiskName)
                )
                concentrations.add(concentration)
            }

            resultSet.close()
            statement.close()
            connection.close()

        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return concentrations
    }

    override fun getById(id: Int): RemoteYearConcentration? {
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

            var concentration: RemoteYearConcentration? = null

            if (resultSet.next()) {
                concentration = RemoteYearConcentration(
                    id = resultSet.getInt(columnIdName),
                    materialId = resultSet.getInt(columnMaterialIdName),
                    year = resultSet.getInt(columnYearName),
                    value = resultSet.getDouble(columnValueName),
                    carcinogenicRisk = resultSet.getDouble(columnCarcinogenicRiskName),
                    nonCarcinogenicRisk = resultSet.getDouble(columnNonCarcinogenicRiskName)
                )
            }

            resultSet.close()
            preparedStatement.close()
            connection.close()

            return concentration

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return null
    }


    override fun add(concentrations: List<RemoteYearConcentration>) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(insertQuery).use { preparedStatement ->

                for (concentration in concentrations) {

                    preparedStatement.setInt(1, concentration.id)
                    preparedStatement.setInt(2, concentration.year)
                    preparedStatement.setDouble(3, concentration.value)
                    preparedStatement.setInt(4, concentration.materialId)
                    preparedStatement.setDouble(5, concentration.carcinogenicRisk)
                    preparedStatement.setDouble(6, concentration.nonCarcinogenicRisk)

                    preparedStatement.executeUpdate()
                }
            }
        }
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