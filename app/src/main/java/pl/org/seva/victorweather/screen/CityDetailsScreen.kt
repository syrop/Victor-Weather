package pl.org.seva.victorweather.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import pl.org.seva.victorweather.presentation.viewstate.CityDetailsViewState
import pl.org.seva.victorweather.R

@Composable
fun CityDetailsScreen(state: CityDetailsViewState) {

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(state.cityName)
            state.weather?.let { weather ->
                val tempColor = when {
                    weather.temp < 10.0 -> Color.Blue
                    weather.temp > 20.0 -> Color.Red
                    else -> if (isSystemInDarkTheme()) Color.White else Color.Black
                }
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(R.string.temperature), ": ")
                        append(AnnotatedString(weather.temp.toString(), SpanStyle(color = tempColor)))
                    }
                )
                Text("${stringResource(R.string.clouds)}: ${weather.clouds}")
                if (weather.rain != null) {
                    Text("${stringResource(R.string.rain)}: ${weather.rain}")
                }
                if (weather.snow != null) {
                    Text("${stringResource(R.string.snow)}: ${weather.snow}")
                }
                Text("${stringResource(R.string.humidity)}: ${weather.humidity}")
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }

}
