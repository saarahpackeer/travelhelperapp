package com.example.th1;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    public static final String START = "message";
    public static final String END = "message";
    public static final String STARTANDEND = "message";
    public static final String END_POINT = "message";
    public static final String DISTANCE = "message";
    public static final String FUELCONS = "message";
    public static final String FUEL_NEEDED = "message";
    public static final String FUEL_COST = "message";
    public static final double AVG_FCOST = 1.5;
    int distance_int = 0;
    double fuel_cons_double = 0;
    double totalFuel = 0;
    double totalCost = 0;
    String leaving;
    String arriving;

    Button saveTrip, discardButton;
    TextView showLeaving;
    DatabaseHelper myDB;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setTitle(null);

        myDB = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();

        leaving = extras.getString("LEAVING");
        arriving = extras.getString("ARRIVING");

        String selectedChoices = extras.getString("STARTANDEND");
        TextView journey = (TextView)findViewById(R.id.tripDetail2);
        journey.setText(selectedChoices+".");

        String distance = extras.getString("DISTANCE");
        distance_int = Integer.parseInt(distance);
        TextView dis = (TextView)findViewById(R.id.tripDistance2);
        dis.setText(distance+" km");

        String fuelConsumption = extras.getString("FUEL_CONS");
        fuel_cons_double = Double.parseDouble(fuelConsumption);
        TextView fuelc = (TextView)findViewById(R.id.tripFuelC);
        fuelc.setText(fuelConsumption);

        String fuelNeeded = extras.getString("FUEL_NEEDED");
        final double fuel_needed_double = calcFuel();
        TextView fueln = (TextView)findViewById(R.id.tripFuelNeeded2);
        fueln.setText(fuel_needed_double +" litres");

        String fuelCost = extras.getString("FUEL_COST");
        final double fuel_cost_double = calcCost();
        TextView fuelcost = (TextView)findViewById(R.id.tripFuelCost2);
        fuelcost.setText("$"+fuel_cost_double);

        saveTrip = (Button) findViewById(R.id.saveJourney);
        saveTrip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(leaving, arriving, distance_int,
                        fuel_cons_double, fuel_needed_double, fuel_cost_double);
                if (isInserted == true) {

                    Toast.makeText(ResultsActivity.this, "Trip successfully saved!", Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(ResultsActivity.this, "Error, no trip saved", Toast.LENGTH_LONG).show();
                }
            }
        });

        discardButton = (Button) findViewById(R.id.deleteTrip);
        discardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    public double calcFuel() {
        totalFuel = ((distance_int/100) * fuel_cons_double);
        //totalFuel = fuel_cons_double;
        return totalFuel;
    }

    public double calcCost() {
        totalCost = totalFuel*AVG_FCOST;
        return totalCost;
    }


}
