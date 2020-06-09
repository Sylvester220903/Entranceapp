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
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    ArrayList<QuestionModel> questions = new ArrayList();
    ArrayList<Integer> score = new ArrayList();
    QuestionAdapter questionAdapter;
    ListView listView;
    Button submitButton;

    @Override
    public void onPause() {
        super.onPause();

        Context context = getApplicationContext();
        CharSequence text = "Stop Cheating, Start the Test from the beginning again";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        super.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);



        listView = findViewById(R.id.questions_list);
        submitButton = findViewById(R.id.submit_button);
        questions.add(new QuestionModel(
                "1. The equation |x-2|+|x-9|=7 has solution if",
                "x=2",
                "x=9",
                "x>=9",
                "2<=x<=9",
                "D"
        ));
        questions.add(new QuestionModel(
                "2. The number of divisors of the form 4n+2 (n>=1) of the integer 240 is",
                "4",
                "8",
                "10",
                "3",
                "A"
        ));
        questions.add(new QuestionModel(
                "3.The maximum number of third order determinants that can be formed whose entries are only odd digits is",
                "5*9",
                "9^5",
                "5^9",
                "10^9",
                "C"
        ));
        questions.add(new QuestionModel(
                "4.The last two digits of the number 3^400 is",
                "39",
                "29",
                "01",
                "43",
                "C"
        ));
        questions.add(new QuestionModel(
                "5.The line x+3y-2=0 bisects the angle between a pair of straight lines of which one has the equation x-7y+5=0. The equation of the other line is",
                "3x+3y-1=0 ",
                "x-3y+2=0",
                "5x+5y-3=0",
                "5x+5y+3=0",
                "C"
        ));
        questions.add(new QuestionModel(
                "6.The velocity of a particle is given by v=20t^2-100t+50 where v is in metre per sec and t is in sec. The velocity when the acceleration a is zero is",
                "75m/s",
                "-75m/s",
                "150m/s",
                "-150m/s",
                "B"
        ));
        questions.add(new QuestionModel(
                "7.Which of the following transitions in hydrogen atom emits the photon of lowest frequency?",
                "n=2 to n=1",
                "n=4 to n=2",
                "n=4 to n=3",
                "n=3 to n=1",
                "C"
        ));
        questions.add(new QuestionModel(
                "8.Determine the depth of water that contains the same number of waves as 16 cm of glass when each is traversed by the same monochromatic light given u of water and glass are 4/3 and 3/2 respectively.",
                "18 cm",
                "12 cm",
                "15 cm",
                "10 cm",
                "A"
        ));
        questions.add(new QuestionModel(
                "9. The first excitation potential of a given atom is 10.2 volts. Then the ionisation potential must be",
                "20.4v",
                "13.6v",
                "30.6v",
                "40.8v",
                "B"
        ));
        questions.add(new QuestionModel(
                "10.A wire suspended vertically from one end is stretched by a weight of 200N at other end. The wire stretches by 1mm. The energy gained by wire is ",
                "0.1 joule",
                "0.2 joule",
                "0.4 joule",
                "10 joule",
                "A"
        ));
        questions.add(new QuestionModel(
                "11.An electron is accelerated from rest by the application of a potential difference of v volts. What is the percentage change in its de brogile wavelength for a 1% change in V?",
                "0.5% decrease",
                "1% increase",
                "1% decrease",
                "0.25% increase",
                "A"
        ));
        questions.add(new QuestionModel(
                "12.Calculate the mass of the photon with a wavelength corresponding to the series limit of the Balmer transitions of the He ion in kg.",
                "4.22*10^-36",
                "2.24*10^-34",
                "2.42*10^-35",
                "4.22*10^-36",
                "C"
        ));
        questions.add(new QuestionModel(
                "13.Which is not a reducing agent?",
                "hypophosphorus acid",
                "phosphorus acid",
                "nitrous acid",
                "orthophosphoric acid",
                "D"
        ));
        questions.add(new QuestionModel(
                "14.The oxidation number of chromium in chromyl chloride and its geometry is ",
                "+6,tetrahedral",
                "+4,tetrahedral",
                "+6,octahedral",
                "+4,square planar",
                "A"
        ));
        questions.add(new QuestionModel(
                "15.The rearrangement of allyl phenyl ether to yield o-allyl phenol is associated with the name of",
                "Fries",
                "Jacobson",
                "Clasien",
                "Fischer-Hepp",
                "C"
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
                Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
                intent.putExtra("Result", questionAdapter.result);
                startActivity(intent);
                finish();
            }
        });
    }
}