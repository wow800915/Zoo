package com.weiyou.chanting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.weiyou.chanting.ui.theme.ChantingTheme
import androidx.navigation.compose.rememberNavController
import com.weiyou.chanting.ui.accounts.AccountsScreen
import com.weiyou.chanting.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChantingApp()
        }
    }
}

@Composable
fun ChantingApp(){
    ChantingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SetNavigation()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetNavigation(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = navController.currentDestination?.route == "homeScreen",
                    onClick = {
                        navController.navigate("homeScreen") {
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )

                BottomNavigationItem(
                    selected = navController.currentDestination?.route == "accountsScreen",
                    onClick = {
                        navController.navigate("accountsScreen") {
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Accounts") },
                    label = { Text("Accounts") }
                )
            }
        }
    ) { innerPadding ->
        // Use PaddingValues to apply padding to the inner content if needed
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = "homeScreen"
            ) {
                composable("homeScreen") { HomeScreen(navController) }
                composable("accountsScreen") { AccountsScreen(navController) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChantingAppPreview() {
    ChantingApp()
}