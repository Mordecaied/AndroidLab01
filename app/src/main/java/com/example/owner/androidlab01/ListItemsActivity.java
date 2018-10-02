package com.example.owner.androidlab01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {

    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    private Switch btnSwitch;
    private ImageButton imageButton;
    private CheckBox cb;
    private Bitmap imageBitmap;
    private Bundle extras;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        imageButton = (ImageButton) findViewById(R.id.camButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                        imageBitmap = (Bitmap)data.getExtras(). get("data");
                        imageButton.setImageBitmap(imageBitmap);
    }

//                btnSwitch = (Switch) findViewById(R.id.switch1);
//                CharSequence text = "Switch is On";// "Switch is Off"
//                int duration = Toast.LENGTH_SHORT; //= Toast.LENGTH_LONG if Off
//                Toast toast = Toast.makeText(ListItemsActivity.this, text, duration); //this is the ListActivity
//                toast.show(); //display your message box
//                //is this neccessary?
//                btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        if (isChecked) {
//                            //switch enables
//                        } else {
//                            //switch diabled
//                        }
//                    }
//                });
//                cb = (CheckBox) findViewById(R.id.checkBox);
//                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
//                        // 2. Chain together various setter methods to set the dialog characteristic
//                        builder.setMessage(R.string.dialog_message); //Add a dialog message to strings.xml
//
//                        builder.setTitle(R.string.dialog_title);
//                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                Intent resultIntent = new Intent();
//                                resultIntent.putExtra("Response", "Here is my response");
//                                setResult(Activity.RESULT_OK, resultIntent);
//                                finish();
//                            }
//                        })
//                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                })
//                                .show();
//
//        });
//    }

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
