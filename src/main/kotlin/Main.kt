import app.Lab1App
import domain.data.model.LoadedData
import domain.useCases.loadData
import presentation.MainView
import tornadofx.*

private const val materialsExcelFileName = ""
private const val pollutionsExcelFileName = ""
private const val enterprisesExcelFileName = ""

lateinit var data: LoadedData

fun main() {
    data = loadData(
        materialsExcelFileName = materialsExcelFileName,
        pollutionsExcelFileName = pollutionsExcelFileName,
        enterprisesExcelFileName = enterprisesExcelFileName,
    )
    launch<Lab1App>()
}