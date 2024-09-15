package pl.org.seva.victorweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.UseCaseExecutor
import pl.org.seva.victorweather.domain.repository.CitiesRepository
import pl.org.seva.victorweather.domain.repository.WeatherRepository
import pl.org.seva.victorweather.domain.usecase.FetchHistoricalWeatherUseCase
import pl.org.seva.victorweather.domain.usecase.FetchWeatherUseCase
import pl.org.seva.victorweather.domain.usecase.FindCitiesUseCase
import pl.org.seva.victorweather.domain.usecase.GetCityUseCase
import pl.org.seva.victorweather.domain.usecase.FetchHistoricalCitiesUseCase
import pl.org.seva.victorweather.domain.usecase.FetchHistoricalCityUseCase
import pl.org.seva.victorweather.domain.usecase.SaveCityUseCase
import pl.org.seva.victorweather.domain.usecase.SaveWeatherUseCase
import pl.org.seva.victorweather.presentation.CityDetailsPresentation
import pl.org.seva.victorweather.presentation.CityPresentation
import pl.org.seva.victorweather.presentation.HistoryPresentation
import pl.org.seva.victorweather.presentation.WeatherHistoryPresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.CityDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.mapper.CityPresentationToDomainMapper
import pl.org.seva.victorweather.presentation.mapper.WeatherDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.mapper.WeatherPresentationToDomainMapper

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider = ::UseCaseExecutor

    @Provides
    fun providesCityDomainToPresentationMapper() = CityDomainToPresentationMapper()

    @Provides
    fun providesCityPresentationToDomainMapper() = CityPresentationToDomainMapper()

    @Provides
    fun providesWeatherDomainToPresentationMapper() = WeatherDomainToPresentationMapper()

    @Provides
    fun providesWeatherPresentationToDomainMapper() = WeatherPresentationToDomainMapper()

    @Provides
    fun providesFindCitiesUseCase(
        citiesRepository: CitiesRepository,
    ) = FindCitiesUseCase(citiesRepository)

    @Provides
    fun providesGetCityUseCase(
        citiesRepository: CitiesRepository,
    ) = GetCityUseCase(citiesRepository)

    @Provides
    fun providesFetchWeatherUseCase(
        weatherRepository: WeatherRepository,
    ) = FetchWeatherUseCase(weatherRepository)

    @Provides
    fun providesSaveWeatherUseCase(weatherRepository: WeatherRepository) =
        SaveWeatherUseCase(weatherRepository)

    @Provides
    fun providesSaveCityUseCase(
        citiesRepository: CitiesRepository,
    ) = SaveCityUseCase(citiesRepository)

    @Provides
    fun providesLoadCitiesUseCase(
        citiesRepository: CitiesRepository,
    ) = FetchHistoricalCitiesUseCase(citiesRepository)

    @Provides
    fun providesFetchHistoricalWeatherUseCase(
        weatherRepository: WeatherRepository,
    ) = FetchHistoricalWeatherUseCase(weatherRepository)

    @Provides
    fun providesFetchHistoricalCityUseCase(
        citiesRepository: CitiesRepository,
    ) = FetchHistoricalCityUseCase(citiesRepository)

    @Provides
    fun providesCityPresentation(
        cityDomainToPresentationMapper: CityDomainToPresentationMapper,
        cityPresentationToDomainMapper: CityPresentationToDomainMapper,
        findCitiesUseCase: FindCitiesUseCase,
        saveCityUseCase: SaveCityUseCase,
        useCaseExecutorProvider: UseCaseExecutorProvider,
    ) = CityPresentation(
        cityDomainToPresentationMapper,
        cityPresentationToDomainMapper,
        findCitiesUseCase,
        saveCityUseCase,
        useCaseExecutorProvider,
    )

    @Provides
    fun providesCityDetailsPresentation(
        cityDomainToPresentationMapper: CityDomainToPresentationMapper,
        weatherDomainToPresentationMapper: WeatherDomainToPresentationMapper,
        weatherPresentationToDomainMapper: WeatherPresentationToDomainMapper,
        getCityUseCase: GetCityUseCase,
        fetchWeatherUseCase: FetchWeatherUseCase,
        saveWeatherUseCase: SaveWeatherUseCase,
        useCaseExecutorProvider: UseCaseExecutorProvider,
    ) = CityDetailsPresentation(
        cityDomainToPresentationMapper,
        weatherDomainToPresentationMapper,
        weatherPresentationToDomainMapper,
        getCityUseCase,
        fetchWeatherUseCase,
        saveWeatherUseCase,
        useCaseExecutorProvider,
    )

    @Provides
    fun provideHistoryPresentation(
        cityDomainToPresentationMapper: CityDomainToPresentationMapper,
        fetchHistoricalCitiesUseCase: FetchHistoricalCitiesUseCase,
        useCaseExecutorProvider: UseCaseExecutorProvider,
    ) = HistoryPresentation(
        cityDomainToPresentationMapper,
        fetchHistoricalCitiesUseCase,
        useCaseExecutorProvider,
    )

    @Provides
    fun provideWeatherHistoryPresentation(
        weatherDomainToPresentationMapper: WeatherDomainToPresentationMapper,
        cityDomainToPresentationMapper: CityDomainToPresentationMapper,
        fetchHistoricalCityUseCase: FetchHistoricalCityUseCase,
        fetchHistoricalWeatherUseCase: FetchHistoricalWeatherUseCase,
        useCaseExecutorProvider: UseCaseExecutorProvider,
    ) = WeatherHistoryPresentation(
        weatherDomainToPresentationMapper,
        cityDomainToPresentationMapper,
        fetchHistoricalCityUseCase,
        fetchHistoricalWeatherUseCase,
        useCaseExecutorProvider,
    )

}
