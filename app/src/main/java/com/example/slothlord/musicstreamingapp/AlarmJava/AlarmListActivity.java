package com.example.slothlord.musicstreamingapp.AlarmJava;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.slothlord.musicstreamingapp.R;


public class AlarmListActivity extends AppCompatActivity {

    private ListView alarmListView;
    private FloatingActionButton newAlarm;
    // Setup the data source
    //ArrayList<Alarm> itemsArrayList = generateItemsList(); // calls function to get items list
    private Alarms alarms;
    // instantiate the custom list adapter
    AlarmListController adapter;
    private final  int ALARMCODE = 123;

    // get the ListView and attach the adapter




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        newAlarm = (FloatingActionButton) findViewById(R.id.newAlarmButton);
        alarmListView = (ListView) findViewById(R.id.alarmListView);
        alarms = new Alarms();
        //alarms.addAlarm(new Alarm(10,59, "peter"));
        adapter = new AlarmListController(this, alarms.getAlarms());
        alarmListView.setAdapter(adapter);


    }


    public void setNewAlarm(View view){
        //alarms.addAlarm(new Alarm(7,5,"peter"));
        //adapter.notifyDataSetChanged();
        Intent intent = new Intent(this, CreateAlarmActivity.class);
        startActivityForResult(intent, ALARMCODE);

    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == ALARMCODE && resultCode == RESULT_OK ){
            if (intent.hasExtra("hour") && intent.hasExtra("min")){
                System.out.println("helloSTUPID " + intent.getExtras().getInt("hour") + ":" + intent.getExtras().getInt("min"));
                alarms.addAlarm(new Alarm(intent.getExtras().getInt("hour"),intent.getExtras().getInt("min")));
                adapter.notifyDataSetChanged();
            }
        }
    }
}
