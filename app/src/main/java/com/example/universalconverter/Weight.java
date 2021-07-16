package com.example.universalconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Weight extends AppCompatActivity {

    private int onItemSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        Intent intent = getIntent();

        Button convertButton = (Button) findViewById(R.id.convertButton);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weight_units, android.R.layout.simple_spinner_item);
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
                    EditText text1 = (EditText) findViewById(R.id.editTextWeight1);
                    if (!text1.getText().toString().equals("")) { //if it's not empty
                        double value1 = Double.parseDouble(text1.getText().toString());
                        String unitSelected = spinner1.getSelectedItem().toString();
                        String otherUnit = spinner2.getSelectedItem().toString();
                        double result = process(value1, unitSelected, otherUnit); //we call the process with the value entered and the format of in and out
                        String resultText;
                        if (String.valueOf(result).length() > 10) {
                            NumberFormat formatter = new DecimalFormat("0.######E0"); //this is used to format the text as wanted if too long
                            resultText = formatter.format(result) + " " + otherUnit + ".";
                        } else {
                            resultText = result + " " + otherUnit + ".";
                        }

                        TextView text2 = (TextView) findViewById(R.id.editTextWeight2);


                        text2.setText(resultText);
                    }
                }
            }
        });
    }

    protected double process(double enteredValue, String thisUnit, String otherUnit) { //TODO: to be changed accordingly
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
    }
}