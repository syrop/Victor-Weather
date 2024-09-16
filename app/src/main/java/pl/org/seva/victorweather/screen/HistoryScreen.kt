package pl.org.seva.victorweather.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.util.fastForEachReversed
import androidx.navigation.NavHostController
import pl.org.seva.victorweather.destination.CityDetailsDestination
import pl.org.seva.victorweather.destination.WeatherHistoryDestination
import pl.org.seva.victorweather.presentation.HistoryPresentation

@Composable
fun HistoryScreen(
    navController: NavHostController,
    historyPresentation: HistoryPresentation,
) {

    val state = historyPresentation.viewState.collectAsState().value

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            state.cities.fastForEachReversed { city ->
                TextButton(
                    onClick = {
                        navController.navigate(WeatherHistoryDestination(city.uuid))
                    },
                ) {
                    Text(
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        text = city.toString(),
                    )
                }
            }

        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }

}
