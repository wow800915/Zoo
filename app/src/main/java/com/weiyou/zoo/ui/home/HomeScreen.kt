package com.weiyou.zoo.ui.home


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.weiyou.zoo.R
import com.weiyou.zoo.data.models.AreaList
import com.weiyou.zoo.data.models.NetworkResult
import com.weiyou.zoo.ui.components.ErrorAlertDialog
import com.weiyou.zoo.ui.components.LoadingIndicator

val TAG = "HomeScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController) {

    LaunchedEffect(true) {
        homeViewModel.getAreaList()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.HomeScreen))
                },
                actions = {
                    // You can add additional actions if needed
                },
                modifier = Modifier.fillMaxWidth()
            )

            HandleAreaListResult(
                homeViewModel.areaListResult.observeAsState("").value,
                navController
            )

        }
    }

}

@Composable
private fun HandleAreaListResult(areaListResult: Any?, navController: NavController) {
    when (val result = areaListResult) {
        is NetworkResult.Success<*> -> {
            LazyColumn(
                modifier = Modifier.testTag("test_tag_for_lazy_column")
            ) {
                Log.d(TAG, "=====GetAreaListAPI Success=====")
                val areaList =
                    (result as NetworkResult.Success<AreaList>).data?.result?.results.orEmpty()
                items(areaList) { area ->
                    AreaItem(area) {
                        navController.navigate("areaDetail/${area._id}")
                    }
                }
            }
        }

        is NetworkResult.Loading -> {
            Log.d(TAG, "=====GetAreaListAPI Loading=====")
            LoadingIndicator()
        }

        is NetworkResult.Error -> {
            Log.d(TAG, "=====GetAreaListAPI Error=====")
            ErrorAlertDialog(result.error?.message ?: stringResource(id = R.string.Unknown_error))
        }

        else -> {
            // Handle other cases if needed
        }
    }
}