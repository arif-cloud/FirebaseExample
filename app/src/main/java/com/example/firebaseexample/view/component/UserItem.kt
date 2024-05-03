package com.example.firebaseexample.view.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.firebaseexample.model.User

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: User,
    onDeleteUser: () -> Unit
) {
    Row(
        modifier = modifier.clip(RoundedCornerShape(4.dp)).border(2.dp, MaterialTheme.colorScheme.primaryContainer).padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${user.firstName} ${user.lastName}  ${user.age}"
        )
        Icon(
            imageVector = Icons.Default.Delete,
            modifier = Modifier.clickable { onDeleteUser() },
            contentDescription = "delete_icon"
        )
    }
}