package com.example.th1;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateTripActivity extends AppCompatActivity {

    View view;
    EditText inputFuel, txtID;
    Button addTripButton;
    DatabaseHelper myDB;
    int totalD;
    Double totalcost, fuelConsumption;
    double totalCost=0;
    double totalFuel=0;
    String selectedStart, selectedEnd;
    private ArrayList<LocationItem>mLocationList;
    private LocationAdapter mAdapter;
    ListView listData;
    Button viewBtn;
    ArrayList<String>trips = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        getSupportActionBar().setTitle(null);

        myDB = new DatabaseHelper(this);

        initList();


        // Start Point Spinner
        Spinner spinnerLocationStart = findViewById(R.id.txtStart);

        mAdapter = new LocationAdapter(this, mLocationList);
        spinnerLocationStart.setAdapter(mAdapter);

        spinnerLocationStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LocationItem clickedItem = (LocationItem) parent.getItemAtPosition(position);
                selectedStart = clickedItem.getmCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        // End Point Spinner
        Spinner spinnerLocationEnd = findViewById(R.id.txtEnd);

        mAdapter = new LocationAdapter(this, mLocationList);
        spinnerLocationEnd.setAdapter(mAdapter);

        spinnerLocationEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LocationItem clickedItem = (LocationItem) parent.getItemAtPosition(position);
                selectedEnd = clickedItem.getmCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        inputFuel = (EditText) findViewById(R.id.txtFuelConsumption);

        addTripButton = (Button) findViewById(R.id.addTripBtn);

        addTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputFuel.getText().toString().trim().length() > 0) {

                    calcDistance(selectedStart, selectedEnd);
                    Intent intent = new Intent(CreateTripActivity.this, ResultsActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("LEAVING", selectedStart);
                    extras.putString("ARRIVING", selectedEnd);
                    extras.putString("STARTANDEND", "From " + selectedStart + " to " + selectedEnd);
                    extras.putString("DISTANCE", Integer.toString(totalD));
                    extras.putString("FUEL_CONS", inputFuel.getText().toString());
                    extras.putString("FUEL_NEEDED", Double.toString(totalFuel));
                    extras.putString("FUEL_COST", Double.toString(totalCost));
                    intent.putExtras(extras);
                    startActivity(intent);
                    resetDataFields();
                } else {
                    Toast.makeText(CreateTripActivity.this, "The Fuel Consumption field cannot be empty!", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }


    public void showMessage(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }


    public void resetDataFields(){
        inputFuel.setText("");
    }


    // calculates the distance and sets local variable to the value

    public int calcDistance(String selectedStart, String selectedEnd){
        if(selectedStart.equals("Sydney")) {
            if (selectedEnd.equals("Canberra")) {
                totalD = 294;
            } else if(selectedEnd.equals("Melbourne")){
                totalD = 878;
            }
            else if(selectedEnd.equals("Adelaide")){
                totalD = 1375;
            }
            else if(selectedEnd.equals("Perth")){
                totalD = 3942;
            }
            else if(selectedEnd.equals("darwin")){
                totalD = 3979;
            }
            else if(selectedEnd.equals("Brisbane")){
                totalD = 916;
            } else if (selectedStart.equals(selectedEnd)){
                totalD = 0;
            }
        }




        else if(selectedStart.equals("Canberra")) {
            if (selectedEnd.equals("Sydney")) {
                totalD = 294;
            } else if(selectedEnd.equals("Melbourne")){
                totalD = 672;
            }
            else if(selectedEnd.equals("Adelaide")){
                totalD = 1169;
            }
            else if(selectedEnd.equals("Perth")){
                totalD = 3728;
            }
            else if(selectedEnd.equals("darwin")){
                totalD = 3946;
            }
            else if(selectedEnd.equals("Brisbane")){
                totalD = 1197;
            }else if (selectedStart.equals(selectedEnd)){
                totalD = 0;
            }
        }



        else if(selectedStart.equals("Melbourne")) {
            if (selectedEnd.equals("Sydney")) {
                totalD = 878;
            } else if (selectedEnd.equals("Canberra")) {
                totalD = 672;
            } else if (selectedEnd.equals("Adelaide")) {
                totalD = 727;
            } else if (selectedEnd.equals("Perth")) {
                totalD = 3418;
            } else if (selectedEnd.equals("darwin")) {
                totalD = 3753;
            } else if (selectedEnd.equals("Brisbane")) {
                totalD = 1680;
            }else if (selectedStart.equals(selectedEnd)){
                totalD = 0;
            }
        }


        else if(selectedStart.equals("Adelaide")) {
            if (selectedEnd.equals("Sydney")) {
                totalD = 1375;
            } else if (selectedEnd.equals("Canberra")) {
                totalD = 1169;
            } else if (selectedEnd.equals("Melbourne")) {
                totalD = 727;
            } else if (selectedEnd.equals("Perth")) {
                totalD = 2697;
            } else if (selectedEnd.equals("darwin")) {
                totalD = 3032;
            } else if (selectedEnd.equals("Brisbane")) {
                totalD = 2018;
            }else if (selectedStart.equals(selectedEnd)){
                totalD = 0;
            }
        }


        else if(selectedStart.equals("Perth")) {
            if (selectedEnd.equals("Sydney")) {
                totalD = 3942;
            } else if (selectedEnd.equals("Canberra")) {
                totalD = 3728;
            } else if (selectedEnd.equals("Melbourne")) {
                totalD = 3418;
            } else if (selectedEnd.equals("Adelaide")) {
                totalD = 2697;
            } else if (selectedEnd.equals("darwin")) {
                totalD = 4041;
            } else if (selectedEnd.equals("Brisbane")) {
                totalD = 4314;
            }else if (selectedStart.equals(selectedEnd)){
                totalD = 0;
            }
        }

        else if(selectedStart.equals("darwin")) {
            if (selectedEnd.equals("Sydney")) {
                totalD = 3979;
            } else if (selectedEnd.equals("Canberra")) {
                totalD = 3946;
            } else if (selectedEnd.equals("Melbourne")) {
                totalD = 3753;
            } else if (selectedEnd.equals("Adelaide")) {
                totalD = 3032;
            } else if (selectedEnd.equals("Perth")) {
                totalD = 4041;
            } else if (selectedEnd.equals("Brisbane")) {
                totalD = 3425;
            }else if (selectedStart.equals(selectedEnd)){
                totalD = 0;
            }
        }

        else if(selectedStart.equals("Brisbane")) {
            if (selectedEnd.equals("Sydney")) {
                totalD = 916;
            } else if (selectedEnd.equals("Canberra")) {
                totalD = 1197;
            } else if (selectedEnd.equals("Melbourne")) {
                totalD = 1680;
            } else if (selectedEnd.equals("Adelaide")) {
                totalD = 2018;
            } else if (selectedEnd.equals("Perth")) {
                totalD = 4314;
            } else if (selectedEnd.equals("Brisbane")) {
                totalD = 3425;
            }else if (selectedStart.equals(selectedEnd)){
                totalD = 0;
            }
        }
        return totalD;
    }


    // initializes the spinner with images and values
    private void initList() {
        mLocationList = new ArrayList<>();

        mLocationList.add(new LocationItem("Sydney", R.drawable.sydney));
        mLocationList.add(new LocationItem("Canberra", R.drawable.canberra));
        mLocationList.add(new LocationItem("Melbourne", R.drawable.melbourne));
        mLocationList.add(new LocationItem("Adelaide", R.drawable.adelaide));
        mLocationList.add(new LocationItem("Perth", R.drawable.perth));
        mLocationList.add(new LocationItem("Darwin", R.drawable.darwin));
        mLocationList.add(new LocationItem("Brisbane", R.drawable.brisbane));
    }




}





