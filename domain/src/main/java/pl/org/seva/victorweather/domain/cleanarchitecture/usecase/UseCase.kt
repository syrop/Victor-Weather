package pl.org.seva.victorweather.domain.cleanarchitecture.usecase

import kotlinx.coroutines.CoroutineScope

interface UseCase<REQUEST, RESULT> {

    suspend fun execute(
        input: REQUEST,
        coroutineScope: CoroutineScope,
        onResult: (RESULT) -> Unit
    )

}
