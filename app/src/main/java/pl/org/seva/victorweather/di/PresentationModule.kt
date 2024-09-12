package pl.org.seva.victorweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.UseCaseExecutor
import pl.org.seva.victorweather.domain.repository.CitiesRepository
import pl.org.seva.victorweather.domain.usecase.FindCitiesUseCase
import pl.org.seva.victorweather.presentation.CityPresentation
import pl.org.seva.victorweather.presentation.architecture.UseCaseExecutorProvider
import pl.org.seva.victorweather.presentation.mapper.GeocodingDomainToPresentationMapper

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider = ::UseCaseExecutor

    @Provides
    fun providesGeocodingDomainToPresentationMapper() = GeocodingDomainToPresentationMapper()

    @Provides
    fun providesFindCitiesUseCase(
        citiesRepository: CitiesRepository,
    ) = FindCitiesUseCase(citiesRepository)

    @Provides
    fun providesCityPresentation(
        geocodingDomainToPresentationMapper: GeocodingDomainToPresentationMapper,
        findCitiesUseCase: FindCitiesUseCase,
        useCaseExecutorProvider: UseCaseExecutorProvider,
    ) = CityPresentation(
        geocodingDomainToPresentationMapper,
        findCitiesUseCase,
        useCaseExecutorProvider,
    )

}
