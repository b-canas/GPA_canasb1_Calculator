package com.example.GPA_canasb1_Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.*;

public class MainActivity extends AppCompatActivity {

    private static int NUM_OF_GRADES = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void event(View v) {
        Button btnEvent = (Button) v;

        if ((btnEvent.getText()).equals("Calculate GPA")) {

            if (isFieldsFilled()) {
                calculateGPA(v);
                btnEvent.setText("Clear Form");
            }

        }
        else if ((btnEvent.getText()).equals("Clear Form")) {
            clearForm(v);
            btnEvent.setText("Calculate GPA");
        }
    }

    public boolean isFieldsFilled()
    {
        boolean filled = true;
        int[] editTextViewIds = new int[] {R.id.grade1, R.id.grade2, R.id.grade3, R.id.grade4, R.id.grade5};

        Context context = getApplicationContext();
        EditText input;

        for (int i=0; i < editTextViewIds.length; i++) {
            input = (EditText)findViewById(editTextViewIds[i]);

            if ((input.getText().toString()).equals("")) {
                filled = false;
                break;
            }
        }

        if (!filled) {
            String message = "Please fill all fields";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }

        return filled;
    }

    public void calculateGPA(View v)
    {
        double average = 0;
        double sum = 0;
        double[] userGrades = new double[NUM_OF_GRADES]; //used to store user input from grades form
        EditText userInput; //EditText Views are used for grade input
        TextView output = (TextView)findViewById(R.id.result); //will be used to show result

        userInput = (EditText)findViewById(R.id.grade1);
        userGrades[0] = Double.parseDouble(userInput.getText().toString());

        userInput = (EditText)findViewById(R.id.grade2);
        userGrades[1] = Double.parseDouble(userInput.getText().toString());

        userInput = (EditText)findViewById(R.id.grade3);
        userGrades[2] = Double.parseDouble(userInput.getText().toString());

        userInput = (EditText)findViewById(R.id.grade4);
        userGrades[3] = Double.parseDouble(userInput.getText().toString());

        userInput = (EditText)findViewById(R.id.grade5);
        userGrades[4] = Double.parseDouble(userInput.getText().toString());

        for (int i=0; i < NUM_OF_GRADES; i++) {
            sum += userGrades[i];
        }

        if (sum > 0) {
            average = sum/userGrades.length;

            String avgStr = format("The average is %.2f", average);
            output.setText(avgStr);
        }
        else {
            output.setText("The average is 0.00");
        }

        changeBgColor(average);
    }

    public void clearForm(View v)
    {
        EditText userInput; //EditText Views are used for grade input
        int[] editTextViewIds = new int[] {R.id.grade1, R.id.grade2, R.id.grade3, R.id.grade4, R.id.grade5};
        TextView output = (TextView)findViewById(R.id.result); //will be used to show result

        //reset background color
        ScrollView form = (ScrollView)findViewById(R.id.CL_form);
        form.setBackgroundColor(Color.WHITE);

        try {
            for (int i=0; i < editTextViewIds.length; i++) {
                userInput = (EditText)findViewById(editTextViewIds[i]);
                userInput.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        output.setText("---");
    }

    public void changeBgColor(double avg) {
        ScrollView form = (ScrollView)findViewById(R.id.CL_form);

        if (avg >= 80) {
            form.setBackgroundColor(Color.GREEN);
        }
        else if (avg >= 61) {
            form.setBackgroundColor(Color.YELLOW);
        }
        else {
            form.setBackgroundColor(Color.RED);
        }
    }
}