package com.example.toshiba.alarmexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    TextView textView;
    int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker)findViewById(R.id.timepicker);
        textView = (TextView)findViewById(R.id.textview);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
             mHour = hourOfDay;
                mMinute = minute;
                textView.setText(textView.getText().toString()+" "+mHour+": "+mMinute);
            }
        });
    }
    public void setTimer(View v) {
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Date date = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);

        cal_alarm.set(Calendar.HOUR_OF_DAY,mHour);
        cal_alarm.set(Calendar.MINUTE,mMinute);
        cal_alarm.set(Calendar.SECOND,0);

        if (cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }
        Intent i = new Intent(MainActivity.this, Mybroadcastrec.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 2444, i,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "Alarm is set",Toast.LENGTH_SHORT).show();
    }
    }
