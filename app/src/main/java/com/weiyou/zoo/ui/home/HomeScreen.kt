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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.weiyou.zoo.R
import com.weiyou.zoo.data.models.AreaList
import com.weiyou.zoo.data.models.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController) {

    val TAG = "HomeScreen"

    LaunchedEffect(true) {
        homeViewModel.getAreaList()
    }

    val areaListResult by homeViewModel.areaListResult.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                title = {
                    Text(text = "Your Title")
                },
//                navigationIcon = {
//                    // Back arrow button
//                    IconToggleButton(checked = false, onCheckedChange = {}) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
//                    }
//                },
                actions = {
                    // You can add additional actions if needed
                },
                modifier = Modifier.fillMaxWidth()
            )


            LazyColumn {
                when (areaListResult) {
                    is NetworkResult.Success -> {
                        var areaList = (areaListResult as NetworkResult.Success<AreaList>).data?.result?.results

                        items(areaList.orEmpty()) { area ->
//                            SampleItem(area)
                            SampleItem(area) {
                                // Navigate to AreaDetailScreen when item is clicked
                                navController.navigate("areaDetail/${area._id}")
                            }
                        }

                    }

                    is NetworkResult.Loading -> {
                        Log.d("IANIAN", "HomeScreen: Loading")
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
                        Log.d("IANIAN", "HomeScreen: Error")
                        // Optionally, show an error dialog or handle error UI
                        item {
                            ErrorAlertDialog(
                                (areaListResult as NetworkResult.Error).error?.message
                                    ?: "Unknown error"
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

@Composable
fun SampleItem(area: AreaList.Area, onItemClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onItemClick.invoke() } // Handle item click
    ) {
//
//        AsyncImage(
//            model = area.e_pic_url,
//            contentDescription = null,
//            modifier = Modifier
//                .size(48.dp) // Adjust the size as needed
//                .padding(end = 8.dp), // Adjust the padding as needed
//        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null, // Provide a meaningful content description
            modifier = Modifier
                .size(48.dp) // Adjust the size as needed
                .padding(end = 8.dp) // Adjust the padding as needed
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            area.e_name?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            area.e_info?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 2 // 設定最大行數為2
                )
            }
        }
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ErrorAlertDialog(error: String) {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = { /* Handle dialog dismissal if needed */ },
        title = { Text(text = "Error") },
        text = { Text(text = "An error occurred: $error") },
        confirmButton = {
            TextButton(
                onClick = {
                    // Handle button click if needed
                    Toast.makeText(context, "AlertDialog dismissed", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = "Dismiss")
            }
        }
    )
}