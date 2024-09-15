package pl.org.seva.victorweather.datasource.mapper

import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.datasource.model.CityHistoricalDataSourceModel

class CityDataToDataSourceMapper {

    fun toDataSource(input: CityDataModel): CityHistoricalDataSourceModel {
        return CityHistoricalDataSourceModel(
            input.uuid,
            input.name,
            input.state,
            input.country,
        )
    }

}
