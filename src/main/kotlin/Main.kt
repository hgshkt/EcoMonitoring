import app.Lab1App
import domain.data.model.LoadedData
import domain.useCases.loadData
import presentation.MainView
import tornadofx.launch
import util.pathToResources

lateinit var loadedData: LoadedData

fun main() {
    loadedData = loadData(
        materialsExcelFileName = pathToResources("речовини.xlsx"),
        pollutionsExcelFileName = pathToResources("викиди.xlsx"),
        enterprisesExcelFileName = pathToResources("підприємства.xlsx")
    )
    launch<Lab1App>(arrayOf(MainView::data to loadedData))
}