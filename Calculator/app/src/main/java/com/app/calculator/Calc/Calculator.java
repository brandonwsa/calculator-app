package com.app.calculator.Calc;

public class Calculator {

    private double miles;
    private double speed;
    private double time;

    public Calculator(double miles, double speed, double time){
        this.miles = miles;
        this.speed = speed;
        this.time = time;

    }

    /**
     * Determines what needs to be calculated.
     * @return "nothing" if nothing to calc. "miles" if miles to calc. "time" if time to calc. "speed" if speed to calc.
     */
    public String whatToCalc(){
        int i = 0; //used to count how many input fields were left blank.
                    //to be valid, at most, i should = 1.

        //check to see how many input fields were left blank.
        if (miles == 0){
            i++;
        }

        if (speed == 0){
            i++;
        }

        if (time == 0){
            i++;
        }

        //check to see what to calculate based on number of input fields left blank.
        if (i == 3){
            return "nothing";
        }
        else if (i == 2){
            return "nothing";
        }
        else if (miles != 0 && speed != 0 && time != 0){
            return "nothing";
        }
        else if (miles == 0){
            return "miles";
        }
        else if (speed == 0){
            return "speed";
        }
        else if (time == 0){
            return "time";
        }

        //should never reach.
        return "nothing";
    }

    /**
     * calculates speed to travel to travel all of the miles in provided time.
     * @return speed
     */
    public double calcSpeed(){
        return miles/time;
    }

    /**
     * Calculates time in which you would travel all of the miles provided at the time provided.
     * @return time
     */
    public double calcTime(){
        return miles/speed;
    }

    /**
     * calculates how many miles you can travel going the provided speed in the provided time.
     * @return miles
     */
    public double calcMiles(){
        return speed*time;
    }
}
