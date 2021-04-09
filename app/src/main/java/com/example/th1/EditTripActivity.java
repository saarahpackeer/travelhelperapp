package com.example.th1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditTripActivity extends AppCompatActivity {

    public static final String TRIPID = "message";
    String trip_id;
    int trip_id_int;
    EditText inputEditFuel;
    EditText editFuelCons;
    Button editTripButton;
    DatabaseHelper myDB;
    int totalD;
    String selectedStart, selectedEnd;
    private ArrayList<LocationItem> mLocationList;
    private LocationAdapter mAdapter;
    ArrayList<String>trips = new ArrayList<>();
    TextView showID;

    public static final double AVG_FCOST = 1.5;
    int distance_int;
    double fuel_cons_double = 0;
    double totalFuel = 0;
    double totalCost = 0;
    double fuel_needed_double;
    double fuel_cost_double;
    String fuel_cons;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);

        getSupportActionBar().setTitle("Saved Trips");

        myDB = new DatabaseHelper(this);


        Bundle extras = getIntent().getExtras();
        trip_id = extras.getString("TRIPID");
        trip_id_int = Integer.parseInt(trip_id);

        showID = (TextView) findViewById(R.id.retrievedID);
        showID.setText("#"+trip_id);


        initList();

        // Start Point Spinner
        Spinner spinnerLocationStart = findViewById(R.id.txtEditStart);

        mAdapter = new LocationAdapter(this, mLocationList);
        spinnerLocationStart.setAdapter(mAdapter);

        spinnerLocationStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LocationItem clickedItem = (LocationItem) parent.getItemAtPosition(position);
                selectedStart = clickedItem.getmCity();
                Toast.makeText(EditTripActivity.this, selectedStart + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        // End Point Spinner
        Spinner spinnerLocationEnd = findViewById(R.id.txtEditEnd);

        mAdapter = new LocationAdapter(this, mLocationList);
        spinnerLocationEnd.setAdapter(mAdapter);

        spinnerLocationEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LocationItem clickedItem = (LocationItem) parent.getItemAtPosition(position);
                selectedEnd = clickedItem.getmCity();
                Toast.makeText(EditTripActivity.this, selectedEnd + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        inputEditFuel = (EditText) findViewById(R.id.inputEditFuel);

        editTripButton = (Button) findViewById(R.id.editButton);

        editTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcDistance(selectedStart, selectedEnd);
                fuel_cons = inputEditFuel.getText().toString();
                fuel_cons_double = Double.parseDouble(fuel_cons);
                calcFuel();
                calcCost();
                fuel_needed_double = totalFuel;
                fuel_cost_double = totalCost;

                updateRecord();

                Intent intent = new Intent(EditTripActivity.this,ViewAllActivity.class);
                startActivity(intent);


            }
        });



    }

    public void updateRecord() {
        boolean isUpdated = myDB.updateRecord(trip_id,
                selectedStart,
                selectedEnd,
                totalD,
                fuel_cons_double,
                fuel_needed_double,
                fuel_cost_double);
        if (isUpdated == true) {
            Toast.makeText(EditTripActivity.this, "Record Updated",
                    Toast.LENGTH_LONG).show();
            //resetDataFields();
        } else {
            Toast.makeText(EditTripActivity.this, "Record Not Updated",
                    Toast.LENGTH_LONG).show();
        }
    }





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


    public double calcFuel() {
        totalFuel = ((totalD/100) * fuel_cons_double);
        return totalFuel;
    }

    public double calcCost() {
        totalCost = totalFuel*AVG_FCOST;
        return totalCost;
    }

}
