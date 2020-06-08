package com.example.entranceexamapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuestionAdapter extends ArrayAdapter {
    ArrayList<QuestionModel> questions;
    ArrayList<Integer> score;
    TextView question;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;

    Context mContext;

    public QuestionAdapter(Context context, int resource, ArrayList<QuestionModel> questions, ArrayList<Integer> score) {
        super(context, resource, questions);
        mContext = context;
        this.questions = questions;
        this.score = score;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItem = convertView;
        if (listItem == null) {
            listItem = inflater.inflate(R.layout.question, null);
        }

        question = listItem.findViewById(R.id.question);
        radioButton1 = listItem.findViewById(R.id.radio1);
        radioButton2 = listItem.findViewById(R.id.radio2);
        radioButton3 = listItem.findViewById(R.id.radio3);
        radioButton4 = listItem.findViewById(R.id.radio4);
        radioGroup = listItem.findViewById(R.id.radio_group);

        question.setText(questions.get(position).question);
        radioButton1.setText(questions.get(position).optionA);
        radioButton2.setText(questions.get(position).optionB);
        radioButton3.setText(questions.get(position).optionC);
        radioButton4.setText(questions.get(position).optionD);

        final View finalListItem = listItem;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton selected = finalListItem.findViewById(id);

                if (selected.getText().equals(questions.get(position).answer)){
                    score.set(position, 1);
                }else{
                    score.set(position, 0);
                }

                String val = "";
                for (int j=0; j<questions.size(); j++){
                    val+=Integer.toString(score.get(j));
                }
                Log.v("score", val);

            }
        });

        return listItem;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}