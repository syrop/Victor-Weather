package pl.org.seva.victorweather.datasource.mapper

import pl.org.seva.victorweather.data.model.CityDataModel
import java.util.UUID

class CityDataSourceToDataMapper {

    fun toData(input: pl.org.seva.victorweather.datasource.model.api.CityDataSourceModel): CityDataModel {
        return CityDataModel(
            input.uuid ?: UUID.randomUUID().toString(),
            input.name,
            input.state,
            input.lat,
            input.lon,
            input.country,
        )
    }

    fun toData(input: pl.org.seva.victorweather.datasource.model.db.CityDataSourceModel): CityDataModel {
        return CityDataModel(
            input.uuid,
            input.name,
            input.state,
            Double.NaN,
            Double.NaN,
            input.country,
        )
    }

}
