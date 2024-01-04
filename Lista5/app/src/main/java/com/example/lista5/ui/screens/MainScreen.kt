package com.example.lista5.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.lista5.Routes
import com.example.lista5.viewModel.SubjectViewModel


@Composable
fun MainScreen(navCon: NavHostController, viewModel: SubjectViewModel) {

    val subjects by viewModel.subjectState.collectAsStateWithLifecycle()
    var total = 0f
    for(s in subjects) {
        total += s.grade
    }

    Column(modifier = Modifier.padding(2.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(subjects.size) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable { navCon.navigate(Routes.Edit.route + "/$it") }
                        .graphicsLayer {
                            shape = RoundedCornerShape(10.dp)
                            clip = true
                        }
                        .background(Color(220, 220, 255))
                        .wrapContentHeight()
                )
                {
                    Text(
                        text = subjects[it].name,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
                    )
                    Text(
                        text = "${subjects[it].grade}",
                        fontSize = 26.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 15.dp, top = 10.dp, bottom = 10.dp)
                    )
                }

            }
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .graphicsLayer {
                shape = RoundedCornerShape(10.dp)
                clip = true
            }
            .background(Color(220, 220, 255))
        ) {
            Text(
                text = "Srednia ocen",
                fontSize = 26.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
            )

            Text(
                text = String.format("%.2f", total/subjects.size),
                fontSize = 26.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp, top = 10.dp, bottom = 10.dp)
            )
        }


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, end = 4.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = { navCon.navigate(Routes.Add.route) }
        ) {
            Text(text = "Add")
        }
    }
}