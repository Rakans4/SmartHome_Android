package com.example.android.smarthome.MicroController;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.android.smarthome.Adapters.microController_Category_spinner_adapter;
import com.example.android.smarthome.Devices.Add_new_device;
import com.example.android.smarthome.Devices.RetrieveListOfDevicesBoundary;
import com.example.android.smarthome.Devices.RetrieveSpecificDeviceBoundary;
import com.example.android.smarthome.R;
import java.util.ArrayList;


public class Add_new_microController extends AppCompatActivity {

    private Spinner microControllerSpinner;

    private EditText microControllerRoomEditText;

    private Button add_microController_button;

    private microController_Category_spinner_adapter microControllerSpinnerAdapter;

    private ArrayList<MicroController_Category> microController_categoryArrayList = new ArrayList<>();

    private String selectedMicroControllerName;

    private int selectedMicroController;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_microcontroller);
        CreateViews();
        CreateSpinnerAdapter();
        attachSpinnerToListener();
    }


    private ArrayList<MicroController_Category> CreateMicroControllerCategory(){

        microController_categoryArrayList.add(new MicroController_Category("Arduino Uno R3" , R.drawable.arduinoicon , 0 ));


        return microController_categoryArrayList;
    }


    private void CreateSpinnerAdapter(){

        microControllerSpinnerAdapter =  new microController_Category_spinner_adapter(this , CreateMicroControllerCategory());

        microControllerSpinner = findViewById(R.id.spinner_microController_Lay_add_new_microController);

        microControllerSpinner.setAdapter(microControllerSpinnerAdapter);

    }


    private void attachSpinnerToListener(){


        microControllerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                MicroController_Category MicroController = (MicroController_Category) parent.getItemAtPosition(position);

                selectedMicroControllerName = MicroController.getCategoryName(); // 0 Light Bulb
                selectedMicroController = MicroController.getType();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedMicroController = -1;

            }
        });


    }

    private void CreateViews(){



        microControllerRoomEditText = findViewById(R.id.micro_controller_room_Lay_add_new_microcontroller);

        add_microController_button = findViewById(R.id.add_microController_Lay_add_new_microController);

        add_microController_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDeviceToDB();

            }
        });


    }


    private void addDeviceToDB(){


        String microControllerRoom = microControllerRoomEditText.getText().toString().trim();


        if(TextUtils.isEmpty(microControllerRoom) ){

            Toast.makeText(getApplicationContext() , "Please Specify Room " , Toast.LENGTH_LONG).show();
            return;

        }




        if(selectedMicroController == -1){

            Toast.makeText(getApplicationContext() , "Please select MicroController Category" , Toast.LENGTH_LONG).show();
            return;

        }

        /*
        ContentValues contentValues = new ContentValues();

        contentValues.put(Schema.Device.NAME , deviceName);
        contentValues.put(Schema.Device.PIN , devicePin);
        contentValues.put(Schema.Device.ROOM , deviceRoom);
        contentValues.put(Schema.Device.TYPE , selectedCategory);

        getContentResolver().insert(Schema.Device.CONTENT_URI, contentValues);
        */
        returnToPreviousLayout();


    }


    private void returnToPreviousLayout(){

        Intent Intent = new Intent(Add_new_microController.this, RetrieveListOfDevicesBoundary.class);


        startActivity(Intent);


    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        returnToPreviousLayout();

                    }
                }).create().show();


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                new AlertDialog.Builder(this)
                        .setTitle("Really Exit?")
                        .setMessage("Are you sure you want to exit?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                returnToPreviousLayout();

                            }
                        }).create().show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}