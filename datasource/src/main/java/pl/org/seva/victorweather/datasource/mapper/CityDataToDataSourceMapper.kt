package pl.org.seva.victorweather.datasource.mapper

import pl.org.seva.victorweather.data.model.CityDataModel
import pl.org.seva.victorweather.datasource.model.db.CityDataSourceModel

class CityDataToDataSourceMapper {

    fun toDataSource(input: CityDataModel): CityDataSourceModel {
        return CityDataSourceModel(
            input.uuid,
            input.name,
            input.state,
            input.country,
        )
    }

}
