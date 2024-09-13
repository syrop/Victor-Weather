package pl.org.seva.victorweather.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import pl.org.seva.victorweather.presentation.CityDetailsPresentation

@Composable
fun CityDetailsScreen(
    cityDetailsPresentation: CityDetailsPresentation,
) {

    val cityName = cityDetailsPresentation.viewState.collectAsState().value.cityName

    Text(cityName)

}
