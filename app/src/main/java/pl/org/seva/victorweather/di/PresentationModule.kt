package pl.org.seva.victorweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.UseCaseExecutor
import pl.org.seva.victorweather.domain.repository.CitiesRepository
import pl.org.seva.victorweather.domain.repository.WeatherRepository
import pl.org.seva.victorweather.domain.usecase.FetchWeatherUseCase
import pl.org.seva.victorweather.domain.usecase.FindCitiesUseCase
import pl.org.seva.victorweather.domain.usecase.GetCityUseCase
import pl.org.seva.victorweather.domain.usecase.LoadCitiesUseCase
import pl.org.seva.victorweather.domain.usecase.SaveCityUseCase
import pl.org.seva.victorweather.presentation.CityDetailsPresentation
import pl.org.seva.victorweather.presentation.CityPresentation
import pl.org.seva.victorweather.presentation.HistoryPresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.CityDomainToPresentationMapper
import pl.org.seva.victorweather.presentation.mapper.CityPresentationToDomainMapper
import pl.org.seva.victorweather.presentation.mapper.WeatherDomainToPresentationMapper

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider = ::UseCaseExecutor

    @Provides
    fun providesCityDomainToPresentationMapper() = CityDomainToPresentationMapper()

    @Provides
    fun provideCityPresentationToDomainMapper() = CityPresentationToDomainMapper()

    @Provides
    fun providesWeatherDomainToPresentationMapper() = WeatherDomainToPresentationMapper()

    @Provides
    fun providesFindCitiesUseCase(
        citiesRepository: CitiesRepository,
    ) = FindCitiesUseCase(citiesRepository)

    @Provides
    fun providesGetCityUseCase(
        citiesRepository: CitiesRepository,
    ) = GetCityUseCase(citiesRepository)

    @Provides
    fun fetchWeatherUseCase(
        weatherRepository: WeatherRepository,
    ) = FetchWeatherUseCase(weatherRepository)

    @Provides
    fun saveCityUseCase(
        citiesRepository: CitiesRepository,
    ) = SaveCityUseCase(citiesRepository)

    @Provides
    fun loadCitiesUseCase(
        citiesRepository: CitiesRepository,
    ) = LoadCitiesUseCase(citiesRepository)

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
        getCityUseCase: GetCityUseCase,
        fetchWeatherUseCase: FetchWeatherUseCase,
        useCaseExecutorProvider: UseCaseExecutorProvider,
    ) = CityDetailsPresentation(
        cityDomainToPresentationMapper,
        weatherDomainToPresentationMapper,
        getCityUseCase,
        fetchWeatherUseCase,
        useCaseExecutorProvider,
    )

    @Provides
    fun provideHistoryPresentation(
        cityDomainToPresentationMapper: CityDomainToPresentationMapper,
        loadCitiesUseCase: LoadCitiesUseCase,
        useCaseExecutorProvider: UseCaseExecutorProvider,
    ) = HistoryPresentation(
        cityDomainToPresentationMapper,
        loadCitiesUseCase,
        useCaseExecutorProvider,
    )

}
