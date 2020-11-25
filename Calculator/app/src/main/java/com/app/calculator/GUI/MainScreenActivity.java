package com.app.calculator.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.calculator.R;

import com.app.calculator.Calc.Calculator;

public class MainScreenActivity extends AppCompatActivity {

    private EditText milesInput;
    private EditText speedInput;
    private EditText timeInput;
    private Button calcButton;
    private Button clearButton;
    private double miles, speed, time;
    private Calculator calc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_activity);


        //get input fields
        milesInput = findViewById(R.id.milesInputText);
        speedInput = findViewById(R.id.speedInputText);
        timeInput = findViewById(R.id.timeInputText);

        //get buttons
        calcButton = findViewById(R.id.calcButton);
        clearButton = findViewById(R.id.clearButton);

        //calc button
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                calculateButtonPressed();
            }
        });

        //clear button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                clearButtonPressed();
            }
        });


    }

    /**
     * Performs the Clear button actions.
     */
    private void clearButtonPressed(){
        //reset input field values.
        miles = 0;
        speed = 0;
        time = 0;

        //reset input field text.
        milesInput.setText("");
        timeInput.setText("");
        speedInput.setText("");
    }

    /**
     * Performs action of the Calculate button being pressed.
     */
    private void calculateButtonPressed(){
        //get input fields
        miles = getMiles();
        speed = getSpeed();
        time = getTime();

        //make calculator with input fields.
        calc = new Calculator(miles, speed, time);

        //check to see what to calculate.
        String whatToCalc = calc.whatToCalc();
        double result;
        switch (whatToCalc){
            case "nothing":
                System.out.println("Nothing to calculate...");
                break;

            case "miles":
                result = calc.calcMiles();
                milesInput.setText(Double.toString(result));
                break;

            case "speed":
                result = calc.calcSpeed();
                speedInput.setText(Double.toString(result));
                break;

            case "time":
                result = calc.calcTime();
                timeInput.setText(Double.toString(result));
                break;

            default:
                System.out.println("Unable to calc");
                break;
        }

        System.out.println("miles: "+miles+"\tspeed: "+speed+"\ttime: "+time);
    }

    /**
     * gets the value of miles input as a double.
     * @return double miles or -1 if failed to parse or 0 if input is null.
     */
    public double getMiles(){
        String sMiles = milesInput.getText().toString();

        //check if left blank
        if (sMiles.equals("")){
            return 0;
        }

        try {
            double miles = Double.parseDouble(sMiles);
            return miles;
        }
        catch(NumberFormatException e){
            System.out.println("Failed to parse miles to double. e: "+e);
        }

        return -1;
    }

    /**
     * gets the value speed input as a double.
     * @return double speed or -1 if failed to parse or 0 if input is null.
     */
    public double getSpeed(){
        String sSpeed = speedInput.getText().toString();

        //check if left blank
        if (sSpeed.equals("")){
            return 0;
        }

        try {
            double speed = Double.parseDouble(sSpeed);
            return speed;
        }
        catch(NumberFormatException e){
            System.out.println("Failed to parse speed to double. e: "+e);
        }

        return -1;
    }

    /**
     * gets the value of time input as a double.
     * @return double time or -1 if failed to parse.
     */
    public double getTime(){
        String sTime = timeInput.getText().toString();

        //check if left blank
        if (sTime.equals("")){
            return 0;
        }

        try {
            //check if time string contains a colon
            if (sTime.contains(":")){
                String[] splittedTime = sTime.split(":", 2);

                //get minutes in decimal format.
                double minutesInDecimal = Double.parseDouble(splittedTime[1]) / 60;

                //get the total time in decimal format.
                double time = Double.parseDouble(splittedTime[0]) + minutesInDecimal;

                return time;
            }
            //if already in decimal format.
            else if (sTime.contains(".")){
                double time = Double.parseDouble(sTime);
                return time;
            }
            //if just a number without a decimal.
            else {
                double time = Double.parseDouble(sTime);
                return time;
            }
        }
        catch (NumberFormatException e){
            System.out.println("Failed to parse time to double. e: "+e);
        }

        return -1;
    }
}
