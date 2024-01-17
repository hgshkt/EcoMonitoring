package data.storage.remote.pollutions

import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.model.RemotePollution
import java.sql.*

class PollutionsMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : PollutionsRemoteStorage {

    private val tableName = "pollutions"

    private val columnEnterpriseIdName = "enterprise_id"
    private val columnMaterialIdName = "material_id"
    private val columnYearName = "year"
    private val columnAmountName = "material_amount"
    private val columnConcentrationName = "concentration"
    private val columnCarcinogenicRiskName = "carcinogenic_risk"
    private val columnCarcinogenicRiskLevelName = "carcinogenic_risk_level"
    private val columnNonCarcinogenicRiskName = "non_carcinogenic_risk"
    private val columnNonCarcinogenicRiskLevelName = "non_carcinogenic_risk_level"
    private val columnDamageName = "damage"
    private val columnTaxName = "tax"

    private val getAllQuery = "SELECT * FROM $tableName"
    private val insertQuery = "INSERT INTO $tableName VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
    private val deleteQuery = "DELETE FROM $tableName WHERE $columnEnterpriseIdName = ? AND " +
            "$columnMaterialIdName = ?"
    private val deleteAllQuery = "DELETE FROM $tableName"

    private val updateDamageQuery = "UPDATE $tableName SET $columnDamageName = ? " +
            "WHERE $columnMaterialIdName = ? AND $columnEnterpriseIdName = ? AND $columnYearName = ?"

    private val updateTaxQuery = "UPDATE $tableName SET $columnTaxName = ? " +
            "WHERE $columnMaterialIdName = ? AND $columnEnterpriseIdName = ? AND $columnYearName = ?"


    override fun getAll(): List<RemotePollution> {
        val pollutions = mutableListOf<RemotePollution>()

        try {
            val connection: Connection = DriverManager.getConnection(
                connectionData.url,
                connectionData.user,
                connectionData.password
            )

            val statement = connection.createStatement()
            val resultSet: ResultSet = statement.executeQuery(getAllQuery)

            while (resultSet.next()) {
                val pollution = RemotePollution(
                    enterpriseId = resultSet.getInt(columnEnterpriseIdName),
                    materialId = resultSet.getInt(columnMaterialIdName),
                    year = resultSet.getInt(columnYearName),
                    materialAmount = resultSet.getDouble(columnAmountName),
                    concentration = resultSet.getDouble(columnConcentrationName),
                    carcinogenicRisk = resultSet.getDouble(columnCarcinogenicRiskName),
                    carcinogenicRiskLevel = resultSet.getString(columnCarcinogenicRiskLevelName),
                    nonCarcinogenicRisk = resultSet.getDouble(columnNonCarcinogenicRiskName),
                    nonCarcinogenicRiskLevel = resultSet.getString(columnNonCarcinogenicRiskLevelName),
                    damage = resultSet.getDouble(columnDamageName),
                    tax = resultSet.getDouble(columnTaxName)
                )
                pollutions.add(pollution)
            }

            resultSet.close()
            statement.close()
            connection.close()

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return pollutions
    }

    override fun add(pollution: RemotePollution) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(insertQuery).use { preparedStatement ->

                preparedStatement.setInt(1, pollution.enterpriseId)
                preparedStatement.setInt(2, pollution.materialId)
                preparedStatement.setInt(3, pollution.year)
                preparedStatement.setDouble(4, pollution.materialAmount)
                preparedStatement.setDouble(5, pollution.concentration)
                preparedStatement.setDouble(6, pollution.carcinogenicRisk)
                preparedStatement.setString(7, pollution.carcinogenicRiskLevel)
                preparedStatement.setDouble(8, pollution.nonCarcinogenicRisk)
                preparedStatement.setString(9, pollution.nonCarcinogenicRiskLevel)
                preparedStatement.setDouble(10, pollution.damage)

                preparedStatement.executeUpdate()
            }
        }
    }


    override fun delete(pollution: RemotePollution) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(deleteQuery).use { prepareStatement ->

                prepareStatement.setInt(1, pollution.enterpriseId)
                prepareStatement.setInt(2, pollution.materialId)

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

    override fun updateDamage(pollution: RemotePollution) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).prepareStatement(updateDamageQuery).use {
            with(it) {
                setDouble(1, pollution.damage)
                setInt(2, pollution.materialId)
                setInt(3, pollution.enterpriseId)
                setInt(4, pollution.year)

                executeUpdate()
            }
        }
    }

    override fun updateTax(pollution: RemotePollution) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).prepareStatement(updateTaxQuery).use {
            with(it) {
                setDouble(1, pollution.tax)
                setInt(2, pollution.materialId)
                setInt(3, pollution.enterpriseId)
                setInt(4, pollution.year)

                executeUpdate()
            }
        }
    }
}