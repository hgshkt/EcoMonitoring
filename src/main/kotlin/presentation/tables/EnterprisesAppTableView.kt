package presentation.tables

import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import domain.model.Enterprise
import domain.useCases.GetEnterprisesFromRemoteRepositoryUseCase
import javafx.scene.Parent
import tornadofx.*

class EnterprisesAppTableView: AppTableView() {

    private var useCase: GetEnterprisesFromRemoteRepositoryUseCase

    override val root: Parent

    init {
        useCase = GetEnterprisesFromRemoteRepositoryUseCase(
            repository = EnterpriseMySQLRepository(
                storage = EnterprisesMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val enterprises = useCase.execute().enterprises

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