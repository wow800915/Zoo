package com.weiyou.zoo.ui.components

import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.weiyou.zoo.R

@Composable
fun ErrorAlertDialog(error: String) {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = { /* Handle dialog dismissal if needed */ },
        title = { stringResource(id = R.string.Error) },
        text = { Text(text = stringResource(id = R.string.error_message, error)) },
        confirmButton = {
            TextButton(
                onClick = {
                    Toast.makeText(context, context.getString(R.string.AlertDialog_dismissed), Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = stringResource(id = R.string.Dismiss))
            }
        }
    )
}