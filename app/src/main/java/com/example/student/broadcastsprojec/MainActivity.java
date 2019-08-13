package com.example.student.broadcastsprojec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_ACTION = "";
    private Receiver localListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    protected void onStart(){
        super.onStart();
        localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener, intentFilter);
    }

    protected void onStop(){
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public void onClick(View view){
        if(view.getId() == R.id.btnStartBroadcast){
            BackgroundServices.startAction(this.getApplicationContext());
        }
    }

    public class Receiver extends BroadcastReceiver {


        public void onReceive(Context context, Intent intent){

            TextView txtViewMsg = (TextView)findViewById(R.id.txtViewMsg);
            String currentText = txtViewMsg.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " + message;
            txtViewMsg.setText(currentText);
        }
    }

}
