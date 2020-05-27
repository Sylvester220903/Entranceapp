package com.example.entranceexamapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionsAdapter {
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> endpoints = new ArrayList<>();

    Context mContext;


    public QuestionsAdapter(Context context,int resource, ArrayList<String> names, ArrayList<String> endpoints) {

        super(context, resource, );
        mContext = context;
        this.names = names;
        this.endpoints = endpoints;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            view = new View(mContext);
            view = inflater.inflate(R.layout.peoplecard, null);

            //convertView = ((Activity) this).getLayoutInflater().inflate(R.layout.blogs, parent, false);


            TextView name = (TextView) view.findViewById(R.id.name);
            TextView endpoint = (TextView) view.findViewById(R.id.endpoint);


            name.setText(names.get(position));
            endpoint.setText(endpoints.get(position));


        }else
        {
            view = (View) convertView;
        }



        return view;
    }
}
