package com.example.owner.androidlab01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class StartActivity extends Activity{

    protected static final String ACTIVITY_NAME = "StartActivity";
    private Button buttonXYZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        buttonXYZ = (Button) findViewById(R.id.testButton);

        buttonXYZ.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this , ListItemsActivity.class);
                startActivityForResult(intent, 50);
           }
       }); }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 50 && resultCode == RESULT_OK){
           Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
           String messagePassed = data.getStringExtra("Response");
            Toast.makeText(StartActivity.this, "ListItemsActivity passed: My information to share", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
