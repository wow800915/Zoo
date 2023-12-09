package com.weiyou.zoo.ui.home


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
                navigationIcon = {
                    // Back arrow button
                    IconToggleButton(checked = false, onCheckedChange = {}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    // You can add additional actions if needed
                },
                modifier = Modifier.fillMaxWidth()
            )


            LazyColumn {
                when (areaListResult) {
                    is NetworkResult.Success -> {
                        Log.d("IANIAN", "HomeScreen76 Success:"+ (areaListResult as NetworkResult.Success<AreaList>).data?.result?.results)
                        // Render your list items here based on the successful data
                        items(50) { index ->
                            SampleItem(index)
                        }
                    }

                    is NetworkResult.Loading -> {
                        Log.d("IANIAN", "HomeScreen: Loading")
                        // Optionally, show a loading indicator
                        item {
                            // Your loading indicator UI here
                            Text(text = "Loading...")
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
fun SampleItem(index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null, // Provide a meaningful content description
            modifier = Modifier
                .size(24.dp) // Adjust the size as needed
                .padding(end = 8.dp) // Adjust the padding as needed
        )
        Text(
            text = "Item $index",
            modifier = Modifier
                .fillMaxWidth()
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