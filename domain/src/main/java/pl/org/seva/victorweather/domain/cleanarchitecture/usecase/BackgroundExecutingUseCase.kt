package pl.org.seva.victorweather.domain.cleanarchitecture.usecase

import kotlinx.coroutines.CoroutineScope

abstract class BackgroundExecutingUseCase<REQUEST, RESULT> : UseCase<REQUEST, RESULT> {

    final override suspend fun execute(
        input: REQUEST,
        coroutineScope: CoroutineScope,
        onResult: (RESULT) -> Unit
    ) {
        val result = executeInBackground(input)
        onResult(result)
    }

    abstract suspend fun executeInBackground(
        request: REQUEST
    ): RESULT

}
