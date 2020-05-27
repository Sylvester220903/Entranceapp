package com.example.entranceexamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    ArrayList<QuestionModel> questions = new ArrayList();
    ArrayList<Integer> score = new ArrayList();
    QuestionAdapter questionAdapter;
    ListView listView;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        listView = findViewById(R.id.questions_list);
        submitButton = findViewById(R.id.submit_button);
        questions.add(new QuestionModel(
                "HEYYYY",
                "A",
                "B",
                "C",
                "D",
                "D"
        ));
        questions.add(new QuestionModel(
                "HEYYYY",
                "A",
                "B",
                "C",
                "D",
                "A"
        ));

        for (int i=0; i<questions.size(); i++){
            score.add(0);
        }
        questionAdapter = new QuestionAdapter(getApplicationContext(), 0, questions, score);
        listView.setAdapter(questionAdapter);

        final SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = 0;
                for (int j=0; j<questions.size(); j++){
                    val+=score.get(j);
                }
                editor.putInt("score", val);
                editor.putInt("total", questions.size());
                editor.commit();
                startActivity(new Intent(QuestionsActivity.this, ResultActivity.class));
                finish();
            }
        });
    }
}
