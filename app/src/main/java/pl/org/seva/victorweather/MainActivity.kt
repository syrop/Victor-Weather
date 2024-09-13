package pl.org.seva.victorweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import pl.org.seva.victorweather.destination.CityDestination
import pl.org.seva.victorweather.destination.CityDetailsDestination
import pl.org.seva.victorweather.destination.HistoryDestination
import pl.org.seva.victorweather.presentation.CityDetailsPresentation
import pl.org.seva.victorweather.presentation.CityPresentation
import pl.org.seva.victorweather.screen.CityDetailsScreen
import pl.org.seva.victorweather.screen.CityScreen
import pl.org.seva.victorweather.screen.HistoryScreen
import pl.org.seva.victorweather.ui.theme.VictorWeatherTheme
import javax.inject.Inject

@Serializable
enum class DrawerDestination {
    City,
    History,
}

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var cityPresentation: CityPresentation

    @Inject
    lateinit var cityDetailsPresentation: CityDetailsPresentation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            var selectedDrawerItem by remember { mutableStateOf(DrawerDestination.City) }
            
            VictorWeatherTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.app_name)) },
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = null,
                                    )
                                }

                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        modifier = Modifier.padding(innerPadding),
                        drawerContent = {
                            ModalDrawerSheet {
                                NavigationDrawerItem(
                                    label = { Text(text = stringResource(R.string.city_label)) },
                                    selected = selectedDrawerItem == DrawerDestination.City,
                                    onClick = {
                                        selectedDrawerItem = DrawerDestination.City
                                        scope.launch {
                                            drawerState.close()
                                        }
                                        navController.navigate(route = CityDestination)
                                    }
                                )
                                NavigationDrawerItem(
                                    label = { Text(text = stringResource(R.string.history_label)) },
                                    selected = selectedDrawerItem == DrawerDestination.History,
                                    onClick = {
                                        selectedDrawerItem = DrawerDestination.History
                                        scope.launch {
                                            drawerState.close()
                                        }
                                        navController.navigate(route = HistoryDestination)
                                    }
                                )
                            }
                        }
                    ) {
                        NavHost(navController = navController, startDestination = CityDestination) {
                            composable<CityDestination> { CityScreen(
                                navController,
                                cityPresentation,
                            ) }
                            composable<HistoryDestination> { HistoryScreen() }
                            composable<CityDetailsDestination> { backStackEntry ->
                                val cityDetailsDestination =
                                    backStackEntry.toRoute<CityDetailsDestination>()
                                cityDetailsPresentation.getCity(
                                    rememberCoroutineScope(),
                                    cityDetailsDestination.city
                                )
                                CityDetailsScreen(
                                    cityDetailsPresentation,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
