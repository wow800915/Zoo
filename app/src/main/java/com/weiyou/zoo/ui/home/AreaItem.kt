package com.weiyou.zoo.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.weiyou.zoo.R
import com.weiyou.zoo.data.models.AreaList

@Composable
fun AreaItem(area: AreaList.Area, onItemClick: () -> Unit) {
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