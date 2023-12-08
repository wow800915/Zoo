package com.weiyou.chanting.ui.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
internal fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController) {


    LaunchedEffect(true) {
        homeViewModel.getAninalList()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {
            items(50) { index ->
                // Vertical LazyColumn with sample items
                SampleItem(index)
            }
        }
    }

}

@Composable
fun SampleItem(index: Int) {
    Text(
        text = "Item $index",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
