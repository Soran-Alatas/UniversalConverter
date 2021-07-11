package com.example.universalconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;

public class Length extends AppCompatActivity {

    private int onItemSelected = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
        Intent intent = getIntent();

        Button convertButton = (Button) findViewById(R.id.convertButton);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.length_units, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected++;
                if (onItemSelected >= 1) {
                    // An item was selected. You can retrieve the selected item using
                    // parent.getItemAtPosition(pos)
                    EditText text1 = (EditText) findViewById(R.id.editTextNumberDecimal1);
                    if (!text1.getText().toString().equals("")) {
                        double value1 = Double.parseDouble(text1.getText().toString());
                        String unitSelected = spinner1.getSelectedItem().toString();
                        String otherUnit = spinner2.getSelectedItem().toString();
                        double result = process(value1, unitSelected, otherUnit);
                        TextView text2 = (TextView) findViewById(R.id.editTextNumberDecimal2);
                        String resultText = String.valueOf(result).substring(0,10) + " " + otherUnit + ".";
                        text2.setText(resultText);
                    }
                }
            }
        });
    }
    protected double process(double enteredValue, String thisUnit, String otherUnit) {
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
    }
}