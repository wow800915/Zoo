package com.weiyou.zoo

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.weiyou.zoo.ui.theme.ChantingTheme
import androidx.navigation.compose.rememberNavController
import com.weiyou.zoo.data.network.NetworkManager
import com.weiyou.zoo.data.network.RemoteDataSource
import com.weiyou.zoo.data.repository.HomeRepository
import com.weiyou.zoo.ui.accounts.AccountsScreen
import com.weiyou.zoo.ui.home.AreaDetailScreen
import com.weiyou.zoo.ui.home.HomeScreen
import com.weiyou.zoo.ui.home.HomeViewModel
import com.weiyou.zoo.utils.createFactory

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
    // 使用 LocalContext 取得當前的 Context
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.primary, // 设置背景颜色
            ) {
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
                val remoteDataSource = RemoteDataSource(NetworkManager)
                val homeRepository = HomeRepository(remoteDataSource)
                val homeViewModel = ViewModelProvider(
                    context as ViewModelStoreOwner, HomeViewModel(homeRepository).createFactory()
                )[HomeViewModel::class.java]

                composable("homeScreen") { HomeScreen(homeViewModel,navController) }
                composable("accountsScreen") { AccountsScreen(navController) }
                //看一下下面的code,是表示直接在MainActivity就已經做好了嗎？
                composable("areaDetail/{areaId}") { backStackEntry ->
                    // Extract areaId from the route
                    val areaId = backStackEntry.arguments?.getString("areaId")
                    AreaDetailScreen(areaId = areaId ?: "",navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChantingAppPreview() {
    ChantingApp()
}