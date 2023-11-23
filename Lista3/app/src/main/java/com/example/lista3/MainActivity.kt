package com.example.lista3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    data class Question(val question: String = "", val A: String = "", val B: String = "", val C: String = "", val D: String = "", val answer: String = "")

    private var questionNumber = 0
    private var question = Question()
    private var points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nextQuestion()
    }

    private fun nextQuestion()
    {
        questionNumber++

        if(questionNumber <= 10){
            question = readData()
            setContent { MainContent() }
        }
        else {
            setContent { EndGame() }
        }
    }

    private fun readData(): Question {
        val istream = assets.open("questions.json")
        val jsonElement = JsonParser.parseReader(InputStreamReader(istream, StandardCharsets.UTF_8))
        val gson = Gson()
        return gson.fromJson(jsonElement.asJsonArray.get((0..500).random()), Question::class.java)
    }

    private fun checkAnswer(selected: String) {
        if (selected == question.A || selected == question.B || selected == question.C || selected == question.D) {
            if (selected == question.A && question.answer == "A"
                || selected == question.B && question.answer == "B"
                || selected == question.C && question.answer == "C"
                || selected == question.D && question.answer == "D"
            )
                points += 10

            nextQuestion()
        }
    }


    @Preview
    @Composable
    private fun EndGame(){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Congratulations",
                fontSize = TextUnit(40f, TextUnitType.Sp),
                modifier = Modifier
                    .weight(3f)
                    .padding(Dp(20f))
            )
            Card(
                modifier = Modifier.weight(7f).wrapContentSize()
            )
            {
                Text(
                    text = "Points: $points",
                    fontSize = TextUnit(50f, TextUnitType.Sp),
                    modifier = Modifier.padding(Dp(50f))
                )
            }
        }
    }

    @Preview
    @Composable
    fun MainContent()
    {
        val selectedValue = remember { mutableStateOf("") }
        val answers = listOf(question.A, question.B, question.C, question.D)

        val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
        val onChangeState: (String) -> Unit = { selectedValue.value = it }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "Question $questionNumber/10",
                fontSize = TextUnit(40f, TextUnitType.Sp),
                modifier = Modifier
                    .padding(Dp(10f))
                    .weight(1f)
            )
            LinearProgressIndicator(
                progress =  questionNumber/10f,
                modifier = Modifier
                    .padding(Dp(20f))
                    .weight(0.55f)
            )
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dp(10f))
                    .weight(3f)
            ){
                Text(
                    text = question.question,
                    fontSize = TextUnit(24f, TextUnitType.Sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dp(10f))
                        .wrapContentSize()
                )
            }
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dp(10f))
                    .weight(4f)
            ){
                Column{
                    answers.forEach { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .selectable(
                                    selected = isSelectedItem(item),
                                    onClick = { onChangeState(item) },
                                    role = Role.RadioButton
                                )
                                .padding(Dp(10f))
                                .weight(1f)
                        ) {
                            RadioButton(
                                selected = isSelectedItem(item),
                                onClick = null
                            )
                            Text(
                                text = item,
                                fontSize = TextUnit(18f, TextUnitType.Sp),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
            Button(
                onClick = { checkAnswer(selectedValue.value) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dp(10f))
                    .weight(1f)
            ) {
                Text("Next", fontSize = TextUnit(20f, TextUnitType.Sp))
            }
        }
    }

}



