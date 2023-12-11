package com.weiyou.zoo.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.weiyou.zoo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaDetailScreen(areaId: String, navController: NavController) {
    // Fetch additional details for the specified areaId if needed
    val TAG = "AreaDetailScreen"

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.AreaDetailScreen))
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.Back),
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                },
                actions = {
                    // You can add additional actions if needed
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display additional details for the area
            Text(text = "Area ID: $areaId", modifier = Modifier.padding(16.dp))

            // You can add more details as needed
        }
    }
}