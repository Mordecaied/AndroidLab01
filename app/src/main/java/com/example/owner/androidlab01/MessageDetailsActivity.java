package com.example.owner.androidlab01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MessageDetailsActivity extends Activity {


    public Button btnDelete;
    public Bundle msgDetails;
    public MessageFragment loadedFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null){

            msgDetails = extras.getBundle("msgDetails");
            loadedFragment = new MessageFragment();
            loadedFragment.setArguments(msgDetails);
            getFragmentManager().beginTransaction().add(R.id.FrameMsgDetPhone, loadedFragment).commit();

        }

    }
    public void deleteMessage(long id, int position){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("id", id);
        resultIntent.putExtra("position", position);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
