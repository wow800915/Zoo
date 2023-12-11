package com.weiyou.zoo.ui.accounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.weiyou.zoo.R

@Composable
fun AccountsScreen(navController: NavHostController) {
    val TAG = "AccountsScreen"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("AccountsScreen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("homeScreen") }) {
            Text(stringResource(id = R.string.Back_to_HomeScreen))
        }
    }
}