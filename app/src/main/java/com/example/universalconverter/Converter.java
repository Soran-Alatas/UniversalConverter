package com.example.universalconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class Converter extends AppCompatActivity {

    private int onItemSelected = 0;
    String myClass ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = null;
        switch(this.getClass().toString())
        {
            case "class com.example.universalconverter.Length":
                setContentView(R.layout.activity_length);
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.length_units, android.R.layout.simple_spinner_item);
                myClass="Length";
                break;

            case "class com.example.universalconverter.Weight":
                setContentView(R.layout.activity_weight);
                // Create an ArrayAdapter using the string array and a default spinner layout
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.weight_units, android.R.layout.simple_spinner_item);
                myClass="Weight";
                break;
            case "class com.example.universalconverter.volume":
                setContentView(R.layout.activity_volume);
                // Create an ArrayAdapter using the string array and a default spinner layout
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.weight_units, android.R.layout.simple_spinner_item);
                myClass="Volume";
                break;
            case "class com.example.universalconverter.Temperature":
                setContentView(R.layout.activity_temperature);
                // Create an ArrayAdapter using the string array and a default spinner layout
                adapter = ArrayAdapter.createFromResource(this,
                        R.array.weight_units, android.R.layout.simple_spinner_item);
                myClass="Temperature";
                break;
            default :
                System.out.println("I'm lost");
                break;
        }
        Intent intent = getIntent();

        Button convertButton = (Button) findViewById(R.id.convertButton);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is because, for some reasons, the buttons gets clicked once when the page is opened. So we do this to avoid
                //actually clicking on the button when we open the page
                onItemSelected++;
                if (onItemSelected >= 1) {
                    // An item was selected. You can retrieve the selected item using
                    // parent.getItemAtPosition(pos)
                    String unitSelected = spinner1.getSelectedItem().toString();
                    String otherUnit = spinner2.getSelectedItem().toString();
                    EditText text1 = null;
                    TextView text2 = null;
                    switch(myClass)
                    {
                        case "Length":
                            text1 = (EditText) findViewById(R.id.editTextNumberDecimal1);
                            text2 = (TextView) findViewById(R.id.editTextNumberDecimal2);
                            break;

                        case "Weight":
                            text1 = (EditText) findViewById(R.id.editTextWeight1);
                            text2 = (TextView) findViewById(R.id.editTextWeight2);
                            break;
                        case "Volume":
                            text1 = (EditText) findViewById(R.id.editTextWeight1);
                            text2 = (TextView) findViewById(R.id.editTextWeight2);
                            break;
                        case "Temperature":
                            text1 = (EditText) findViewById(R.id.editTextWeight1);
                            text2 = (TextView) findViewById(R.id.editTextWeight2);
                            break;
                    }
                    if (!text1.getText().toString().equals("")) { //if it's not empty
                        double value1 = Double.parseDouble(text1.getText().toString());
                        double result = 0;
                        //we call the process with the value entered and the format of in and out
                        switch(myClass)
                        {
                            case "Length":
                                result = process(value1, unitSelected, otherUnit, "length");
                                break;
                            case "Weight":
                                result = process(value1, unitSelected, otherUnit, "weight");
                                break;
                            case "Volume":
                                result = process(value1, unitSelected, otherUnit, "weight");
                                break;
                            case "Temperature":
                                result = process(value1, unitSelected, otherUnit, "weight");
                                break;
                        }
                        String resultText;
                        if (String.valueOf(result).length() > 10) {
                            NumberFormat formatter = new DecimalFormat("0.######E0"); //this is used to format the text as wanted if too long
                            resultText = formatter.format(result) + " " + otherUnit + ".";
                        } else {
                            resultText = result + " " + otherUnit + ".";
                        }



                        text2.setText(resultText);
                    }
                }
            }
        });
    }

    protected double process(double enteredValue, String thisUnit, String otherUnit, String category) {
        switch (category) {
            case "length":
                double thisIntoMeter = 0;
                switch (thisUnit) {
                    case "km":
                        thisIntoMeter = enteredValue * 1000;
                        break;
                    case "m":
                        thisIntoMeter = enteredValue;
                        break;
                    case "dm":
                        thisIntoMeter = enteredValue / 10;
                        break;
                    case "cm":
                        thisIntoMeter = enteredValue / 100;
                        break;
                    case "mm":
                        thisIntoMeter = enteredValue / 1000;
                        break;
                    case "mile":
                        thisIntoMeter = enteredValue / 0.0006213712;
                        break;
                    case "nautical mile":
                        thisIntoMeter = enteredValue / 0.000539957;
                        break;
                    case "foot":
                        thisIntoMeter = enteredValue * 3.28084;
                        break;
                    case "yard":
                        thisIntoMeter = enteredValue * 0.9144;
                        break;
                    case "inch":
                        thisIntoMeter = enteredValue * 0.0254;
                        break;
                    case "light year":
                        thisIntoMeter = enteredValue * 9460800000000000.0;
                        break;
                    case "astronomical unit":
                        thisIntoMeter = enteredValue * 149597870700.0;
                        break;
                    case "parsec":
                        thisIntoMeter = enteredValue / 0.000000000000000032408;
                        break;
                    default:
                        System.out.println("you broke me");
                        break;
                }

                switch (otherUnit) {
                    case "km":
                        return thisIntoMeter / 1000;
                    case "m":
                        return thisIntoMeter;
                    case "dm":
                        return thisIntoMeter * 10;
                    case "cm":
                        return thisIntoMeter * 100;
                    case "mm":
                        return thisIntoMeter * 1000;
                    case "mile":
                        return thisIntoMeter * 0.0006213712;
                    case "nautical mile":
                        return thisIntoMeter * 0.000539957;
                    case "foot":
                        return thisIntoMeter / 3.28084;
                    case "yard":
                        return thisIntoMeter / 1.09361;
                    case "inch":
                        return thisIntoMeter / 0.0254;
                    case "light year":
                        return thisIntoMeter / 9460800000000000.0;
                    case "astronomical unit":
                        return thisIntoMeter / 149597870700.0;
                    case "parsec":
                        return thisIntoMeter * 0.000000000000000032408;
                    default:
                        System.out.println("you broke me");
                        return 0;
                }

            case "weight":
                double thisIntoKg = 0;
                switch (thisUnit) {
                    case "kg":
                        thisIntoKg = enteredValue;
                        break;
                    case "g":
                        thisIntoKg = enteredValue / 1000;
                        break;
                    case "mg":
                        thisIntoKg = enteredValue / 1000000;
                        break;
                    case "Pounds":
                        thisIntoKg = enteredValue * 0.453592;
                        break;
                    case "Ounces":
                        thisIntoKg = enteredValue * 0.0283495;
                        break;
                    case "Stones":
                        thisIntoKg = enteredValue * 6.35029;
                        break;
                    case "Tons":
                        thisIntoKg = enteredValue * 1000;
                        break;
                    default:
                        System.out.println("you broke me");
                        break;
                }

                switch (otherUnit) {
                    case "kg":
                        return thisIntoKg;
                    case "g":
                        return thisIntoKg * 1000;
                    case "mg":
                        return thisIntoKg * 1000000;
                    case "Pounds":
                        return thisIntoKg / 0.453592;
                    case "Ounces":
                        return thisIntoKg / 0.0283495;
                    case "Stones":
                        return thisIntoKg / 6.35029;
                    case "Tons":
                        return thisIntoKg / 1000;
                    default:
                        System.out.println("you broke me");
                        return 0;
                }
            case "volume":
                return 0;
            case "temperature":
                return 0;
            default:
                return 0;
        }
    }
}
