package com.example.lab1_students;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;

    private Boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
        }

        runTimer();
    }

    public void onBtnClick(View view){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String grpNumb = (String) spinner.getSelectedItem();

        Intent intent = new Intent(this,StudentsListActivity.class);
        intent.putExtra(StudentsListActivity.GROUP_NUMBER, grpNumb);

        startActivity(intent);
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.textView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int sec = seconds%60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, sec);
                timeView.setText(time);
                if (isRunning){
                seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
    }

    @Override
    public void onStart(){
        super.onStart();
        isRunning = true;
    }

    @Override
    public void onStop(){
        super.onStop();
        isRunning = false;
    }

    public void onGrpBtnClick(View view){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String grpNum = (String) spinner.getSelectedItem();

        Intent intent = new Intent(this,StudentsGroupActivity.class);
        intent.putExtra(StudentsGroupActivity.GROUP_NUMBER, grpNum);

        startActivity(intent);
    }
}