package pl.org.seva.victorweather.datasource.mapper

import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.datasource.model.CityDataSourceModel
import java.util.UUID

class CityDataSourceToDataMapper {

    fun toData(input: CityDataSourceModel): CityDataModel {
        return CityDataModel(
            input.uuid ?: UUID.randomUUID().toString(),
            input.name,
            input.state,
            input.lat,
            input.lon,
            input.country,
        )
    }

}
