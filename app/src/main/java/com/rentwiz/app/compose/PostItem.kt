package com.rentwiz.app.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PostItem(
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.clickable{}
    ) {
        Row {
            Text(text = "Hi there")
            CircularProgressIndicator()
        }
    }
}