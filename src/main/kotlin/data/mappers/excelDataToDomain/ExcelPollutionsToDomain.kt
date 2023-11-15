package data.mappers.excelDataToDomain

import data.storage.excel.pollutions.model.ExcelPollution

fun ExcelPollution.toDomain(
    mapper: MapperExcelToDomainPollution
) = mapper.toDomain(this)