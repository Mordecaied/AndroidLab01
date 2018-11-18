package com.example.owner.androidlab01;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.zip.Inflater;




public class MessageFragment extends Fragment {

    public Activity activityChoice;

    public TextView tMsgDetails;
    public TextView tMsgId;
    public String msg;
    public long id;
    public int position;
    public View view;
    public Button btnDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        msg = getArguments().getString("msg");
        id = getArguments().getLong("id");
        position = getArguments().getInt("position");

        view = inflater.inflate(R.layout.message_details_fragment, container, false);

        tMsgDetails = (TextView) view.findViewById(R.id.tMsgDetails);
        tMsgId = (TextView)view.findViewById(R.id.tMsgId);

        tMsgDetails.setText("Message:" + msg);
        tMsgId.setText("ID:" + Long.toString(id));

        btnDelete = (Button)view.findViewById(R.id.DeleteMsg);

        //Cited from:https://github.com/nathandoef/CST2335/blob/master/app/src/main/java/com/example/nathan/androidlabs/MessageFragment.java
        switch (activityChoice.getLocalClassName()){
            case "ChatWindow":
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((ChatWindow)activityChoice).deleteMessage(id,position);
                        activityChoice.getFragmentManager().beginTransaction().remove(MessageFragment.this).commit();
                    }
                });
                break;

            case "MessageDetailsActivity":
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MessageDetailsActivity)activityChoice).deleteMessage(id,position);
                    }
                });
                break;
        }
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.activityChoice = activity;
    }
}
