package com.example.jvhm.dailyfin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI(findViewById(R.id.parent));
    }

    private void setupUI(View view){
        hideKeyboard(view);
    }

    private void hideKeyboard(View view) {
        if(!(view instanceof EditText)){
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent e){
                    hideSoftKeyboard();
                    return false;
                }
            });
        }

        if(view instanceof ViewGroup){
            for(int i = 0; i < ((ViewGroup) view).getChildCount(); i++){
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(), 0);
    }

    public void calculateDailyLimit(View view){
        EditText dailyLimit = findViewById(R.id.dailyLimit);
        EditText monthlyMax = findViewById(R.id.monthLimit);
        EditText currentBill = findViewById(R.id.currentBill);
        Double monthlyMaxVal = 0.0;
        Double currentBillVal = 0.0;

        try {
            monthlyMaxVal = Double.parseDouble(monthlyMax.getText().toString());
            currentBillVal = Double.parseDouble(currentBill.getText().toString());
        }
        catch(Exception e){
            dailyLimit.setText("");
        }

        // Get number of days in current month
        Calendar cal = Calendar.getInstance();
        int monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        Double dailyLimitVal = (monthlyMaxVal - currentBillVal) / (monthDays - currentDay);

        dailyLimit.setText(dailyLimitVal.toString());
    }
}
