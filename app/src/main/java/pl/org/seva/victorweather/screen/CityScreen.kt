package pl.org.seva.victorweather.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import pl.org.seva.victorweather.R
import pl.org.seva.victorweather.destination.CityDetailsDestination
import pl.org.seva.victorweather.presentation.CityPresentation

@Composable
fun CityScreen(
    navController: NavHostController,
    cityPresentation: CityPresentation,
) {

    var city by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val state = cityPresentation.viewState.collectAsState().value

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                value = city,
                onValueChange = {
                    val text = it.trim()
                    if (text.isEmpty() || Regex("^[a-zA-Z\\p{L}]+\$").matches(text))
                        city = text
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        cityPresentation.findCities(scope, city)
                    },
                ),
                label = { Text(stringResource(R.string.city)) },
                modifier = Modifier.fillMaxWidth(),
            )
            state.cities.forEach {
                TextButton(
                    onClick = {
                        navController.navigate(CityDetailsDestination(it.uuid))
                    }
                ) {
                    Text(
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        text = "${it.name} (${it.state})",
                    )
                }
            }

        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }

}
