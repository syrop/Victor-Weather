package pl.org.seva.victorweather.presentation.architecture

import pl.org.seva.victorweather.domain.cleanarchitecture.usecase.UseCaseExecutor

typealias UseCaseExecutorProvider =
    @JvmSuppressWildcards () -> UseCaseExecutor
