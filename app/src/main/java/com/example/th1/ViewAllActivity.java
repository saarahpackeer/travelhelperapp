package com.example.th1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {


    DatabaseHelper myDB;
    ArrayList<String>trips = new ArrayList<>();
    ArrayList<String>tripSaved = new ArrayList<>();
    ListView listSaved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        getSupportActionBar().setTitle(null);

        myDB = new DatabaseHelper(this);


        listSaved = (ListView) findViewById(R.id.listViewSaved);


                Cursor res = myDB.viewAllRecords();

                if (res.getCount() == 0) {
                    showMessage("Error", "You have no saved trips!");
                    return;
                }

                while (res.moveToNext()) {
                    trips.add("Leaving " + res.getString(1) + " and arriving at " + res.getString(2) + ".\n" + "The distance travelled will be " + res.getString(4)
                            + " km with fuel consumption at " + res.getString(3) + ".\n" + "The total fuel needed for the trip is "+ res.getString(5)
                            + " litres, costing a total of $" + res.getString(6)+".") ;
                }



                ArrayAdapter<String> savedAdapter = new ArrayAdapter<String>(ViewAllActivity.this,android.R.layout.simple_list_item_1, trips);
                listSaved.setAdapter(savedAdapter);

                listSaved.setAdapter(savedAdapter);

                listSaved.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ViewAllActivity.this, EditTripActivity.class);
                        Bundle extras = new Bundle();
                        int id_incre = (position+1);
                        String trip_id = Integer.toString(id_incre);
                        extras.putString("TRIPID", trip_id);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
    }

    public void viewAll() {
        Cursor res = myDB.viewAllRecords();
        if (res.getCount() == 0) {
            showMessage("Error", "No record found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("one :" + res.getString(1) + "\n");
            buffer.append("two :" + res.getString(2) + "\n");
            buffer.append("three :" + res.getString(3) + "\n");
            buffer.append("four :" + res.getString(4) + "\n");
            buffer.append("five :" + res.getString(5) + "\n");
            buffer.append("seven :" + res.getString(6) + "\n\n");
        }
        showMessage("Record(s) View:", buffer.toString());
    }

    public void showMessage(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }







}
