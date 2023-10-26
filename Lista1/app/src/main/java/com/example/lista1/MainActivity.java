package com.example.lista1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lista1.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Question question;
    private int questionNumber;
    private int points;

    private void readData() throws IOException, JSONException
    {
        InputStream is = getAssets().open("questions.json");
        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        Gson gson = new Gson();
        int rand = Math.abs(new Random().nextInt() % 500);
        question = gson.fromJson(jsonElement.getAsJsonArray().get( rand ).deepCopy(), Question.class);

        binding.questionNumber.setText("Pytanie "+questionNumber+"/10");
        binding.progressBar.setProgress(questionNumber);
        binding.question.setText(question.question);
        binding.answerA.setText(question.A);
        binding.answerB.setText(question.B);
        binding.answerC.setText(question.C);
        binding.answerD.setText(question.D);
    }

    public void nextQuestion(View view)
    {
        int selectedRadio = binding.radioGroup.getCheckedRadioButtonId();
        if(selectedRadio == -1)
            return;

        if(binding.radioGroup.getCheckedRadioButtonId() == binding.answerA.getId() && question.answer == 'A') {
            points += 10;
        } else if (binding.radioGroup.getCheckedRadioButtonId() == binding.answerB.getId() && question.answer == 'B') {
            points += 10;
        } else if (binding.radioGroup.getCheckedRadioButtonId() == binding.answerC.getId() && question.answer == 'C') {
            points += 10;
        } else if (binding.radioGroup.getCheckedRadioButtonId() == binding.answerD.getId() && question.answer == 'D'){
            points += 10;
        }

        if (++questionNumber > 10) {
            endGame();
        } else {
            try {
                readData();
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void endGame()
    {
        binding.radioGroup.setVisibility(View.INVISIBLE);
        binding.questionNumber.setVisibility(View.INVISIBLE);
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.next.setVisibility(View.INVISIBLE);
        binding.totalPoints.setVisibility(View.VISIBLE);
        binding.question.setTextSize(50);
        binding.question.setText("Congratulations");
        binding.totalPoints.setText("Total points: "+points);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        questionNumber = 1;
        points = 0;

        try {
            readData();
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}