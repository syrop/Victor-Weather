package pl.org.seva.victorweather.domain.cleanarchitecture.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.org.seva.victorweather.domain.cleanarchitecture.exception.DomainException
import pl.org.seva.victorweather.domain.cleanarchitecture.exception.UnknownDomainException
import kotlin.coroutines.cancellation.CancellationException

class UseCaseExecutor {

    fun <INPUT, OUTPUT> execute(
        useCase: UseCase<INPUT, OUTPUT>,
        coroutineScope: CoroutineScope,
        value: INPUT,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        coroutineScope.launch {
            try {
                useCase.execute(value, coroutineScope, onSuccess)
            }
            catch (ignore: CancellationException) {
            }
            catch (throwable: Throwable) {
                onException(
                    (throwable as? DomainException)
                        ?: UnknownDomainException(throwable)
                )
            }
        }
    }

}
