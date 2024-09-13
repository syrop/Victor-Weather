package pl.org.seva.victorweather.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.BackgroundExecutingUseCase
import pl.org.seva.victorweather.domain.model.CityDomainModel
import pl.org.seva.victorweather.domain.repository.CitiesRepository

class GetCityUseCase(
    private val citiesRepository: CitiesRepository,
) : BackgroundExecutingUseCase<
        Pair<CoroutineScope, String>,
        Pair<CoroutineScope, CityDomainModel>,
> () {

    override suspend fun executeInBackground(request: Pair<CoroutineScope, String>):
            Pair<CoroutineScope, CityDomainModel> {
        val (scope, uuid) = request
        return scope to withContext(Dispatchers.IO) {
            citiesRepository[uuid]
        }
    }

}
