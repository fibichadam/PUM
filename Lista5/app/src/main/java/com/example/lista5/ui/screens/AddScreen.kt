package com.example.lista5.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lista5.Routes
import com.example.lista5.data.Subject
import com.example.lista5.viewModel.SubjectViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navCon: NavHostController, viewModel: SubjectViewModel) {

    var name by remember { mutableStateOf("") }
    var grade by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(2.dp)) {
        TextField(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .weight(1f)
                .fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            textStyle = LocalTextStyle.current.copy( textAlign = TextAlign.Center, fontSize = 32.sp)
        )
        TextField(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .weight(1f)
                .fillMaxWidth(),
            value = grade,
            onValueChange = { grade = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Grade") },
            textStyle = LocalTextStyle.current.copy( textAlign = TextAlign.Center, fontSize = 32.sp)
        )

        Divider(color = Color.White, modifier = Modifier.weight(6f))

        Button(
            modifier = Modifier
                .weight(.5f)
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                if (name.isNotBlank() && grade.isNotBlank()) {
                    viewModel.addSubject(Subject(0, name, grade.toFloat()))
                    name = ""
                    grade = ""

                    navCon.navigate(Routes.Main.route)
                }
            }
        ) {
            Text(text = "ADD")
        }
    }
}