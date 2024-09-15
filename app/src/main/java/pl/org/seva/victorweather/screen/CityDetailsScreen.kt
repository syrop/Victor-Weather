package pl.org.seva.victorweather.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pl.org.seva.victorweather.presentation.CityDetailsPresentation
import pl.org.seva.victorweather.presentation.viewstate.CityDetailsViewState

@Composable
fun CityDetailsScreen(state: CityDetailsViewState) {

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(state.cityName)
            state.weather?.let { weather ->
                Text(
                    color = when {
                        weather.temp < 10.0 -> Color.Blue
                        weather.temp > 20.0 -> Color.Red
                        else -> if (isSystemInDarkTheme()) Color.White else Color.Black
                    },
                    text = weather.temp.toString(),
                )
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }

}
