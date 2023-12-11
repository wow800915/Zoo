package com.weiyou.zoo.ui.home


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.weiyou.zoo.R
import com.weiyou.zoo.data.models.AreaList
import com.weiyou.zoo.data.models.NetworkResult
import com.weiyou.zoo.ui.components.ErrorAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController) {

    val TAG = "HomeScreen"

    LaunchedEffect(true) {
        homeViewModel.getAreaList()
    }

    val areaListResult by homeViewModel.areaListResult.observeAsState("")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.HomeScreen))                },
                actions = {
                    // You can add additional actions if needed
                },
                modifier = Modifier.fillMaxWidth()
            )


            LazyColumn {
                when (areaListResult) {
                    is NetworkResult.Success<*> -> {
                        Log.d(TAG, "=====GetAreaListAPI Success=====")
                        val areaList = (areaListResult as NetworkResult.Success<AreaList>).data?.result?.results.orEmpty()
                        items(areaList) { area ->
                            AreaItem(area) {
                                navController.navigate("areaDetail/${area._id}")
                            }
                        }

                    }

                    is NetworkResult.Loading -> {
                        Log.d(TAG, "=====GetAreaListAPI Loading=====")
                        // Optionally, show a loading indicator
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp), // Adjust outer padding as needed
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(72.dp) // Adjust the size as needed
                                        .padding(16.dp) // Adjust the padding as needed
                                )
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        Log.d(TAG, "=====GetAreaListAPI Error=====")
                        // Optionally, show an error dialog or handle error UI
                        item {
                            ErrorAlertDialog(
                                (areaListResult as NetworkResult.Error).error?.message
                                    ?: stringResource(id = R.string.Unknown_error)
                            )
                        }
                    }

                    else -> {
                        // Handle other cases if needed
                    }
                }
            }
        }
    }

}
