package presentation.tables

import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterpriseRemoteStorage
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import domain.model.Enterprise
import domain.useCases.GetEnterprisesFromRemoteRepository
import javafx.scene.Parent
import tornadofx.*

class EnterprisesAppTableView: AppTableView() {

    private var useCase: GetEnterprisesFromRemoteRepository

    override val root: Parent

    init {
        useCase = GetEnterprisesFromRemoteRepository(
            repository = EnterpriseMySQLRepository(
                storage = EnterprisesMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val enterprises = useCase.getEnterprisesFromRemoteRepository().enterprises

        root = vbox {
            tableview(enterprises.asObservable()) {
                readonlyColumn("Id", Enterprise::id)
                readonlyColumn("Name", Enterprise::name)
                readonlyColumn("Activity", Enterprise::activity)
                readonlyColumn("Belonging", Enterprise::belonging)
                readonlyColumn("Location", Enterprise::location)
            }
        }
    }
}