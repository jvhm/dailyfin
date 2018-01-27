package com.example.jvhm.dailyfin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateDailyLimit(View view){
        EditText dailyLimity = (EditText) findViewById(R.id.dailyLimit);
        EditText monthlyMax = (EditText) findViewById(R.id.monthLimit);
        EditText currentBill = (EditText) findViewById(R.id.currentBill);
        Double monthlyMaxVal = 0.0;
        Double currentBillVal = 0.0;

        try {
            monthlyMaxVal = Double.parseDouble(monthlyMax.getText().toString());
            currentBillVal = Double.parseDouble(currentBill.getText().toString());
        }
        catch(Exception e){
            dailyLimity.setText("");
        }

        // Get number of days in current month
        Calendar cal = Calendar.getInstance();
        int monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        Double dailyLimit = (monthlyMaxVal - currentBillVal) / (monthDays - currentDay);

        dailyLimity.setText(dailyLimit.toString());
    }
}
