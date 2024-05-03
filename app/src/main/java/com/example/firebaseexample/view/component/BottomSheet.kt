package com.example.firebaseexample.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.firebaseexample.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onCloseBottomSheet : () -> Unit,
    onAddUser : (User) -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf<Int?>(null) }
    var showError by remember { mutableStateOf(false) }
    ModalBottomSheet(
        onDismissRequest = { onCloseBottomSheet() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") }
            )
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") }
            )
            TextField(
                value = if (age == null) "" else age.toString(),
                onValueChange = { age = it.toIntOrNull() },
                label = { Text("Age") }
            )
            Button(
                onClick = {
                    if (firstName.isNotEmpty() && lastName.isNotEmpty() && age != null) {
                        val user = User(firstName, lastName, age ?: 0)
                        onAddUser(user)
                    } else {
                        if (!showError) showError = true
                    }
                },
                content = { Text(text = "Save") }
            )
            if (showError)
                Text(
                    text = "Please fill all fields.",
                    color = Color.Red
                )
        }
    }
}