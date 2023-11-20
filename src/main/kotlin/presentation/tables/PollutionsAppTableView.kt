package presentation.tables

import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.model.Pollution
import domain.useCases.GetPollutionsFromRemoteRepositoryUseCase
import javafx.scene.Parent
import tornadofx.asObservable
import tornadofx.readonlyColumn
import tornadofx.tableview
import tornadofx.vbox

class PollutionsAppTableView : AppTableView() {
    private var useCase: GetPollutionsFromRemoteRepositoryUseCase

    override val root: Parent

    init {
        useCase = GetPollutionsFromRemoteRepositoryUseCase(
            repository = PollutionMySQLRepository(
                storage = PollutionsMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val pollutions = useCase.execute().pollutions

        root = vbox {
            tableview(pollutions.asObservable()) {
                readonlyColumn("Enterprise name", Pollution::enterpriseName)
                readonlyColumn("Material name", Pollution::materialName)
                readonlyColumn("Year", Pollution::year)
                readonlyColumn("MaterialAmount", Pollution::materialAmount)
            }
        }
    }
}