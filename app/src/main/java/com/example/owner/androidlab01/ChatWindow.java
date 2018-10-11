package com.example.owner.androidlab01;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {

    ListView listView;
    EditText textWindow;
    Button sendButton;
    ArrayList<String> chatMessages = new ArrayList<>();
    String input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);



        listView = (ListView)findViewById(R.id._chatView);
        textWindow = (EditText)findViewById(R.id.chatBoxx);
        sendButton = (Button) findViewById(R.id.sendButton);

        final ChatAdapter messageAdapter =new ChatAdapter( this );
        listView.setAdapter (messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = textWindow.getText().toString();
                chatMessages.add(input);
                messageAdapter.notifyDataSetChanged();
                textWindow.setText("");

            }
        });

    }
    public class ChatAdapter extends ArrayAdapter<String>{

        LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
        TextView messageText;


        public ChatAdapter(Context ctx){
            super(ctx,0);
        }

        @Override
        public int getCount() {
            return chatMessages.size();

        }
        @Override
        public String getItem(int position) {
            return chatMessages.get(position);
        }
        @NonNull
        public View getView(int position, View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result;
            if (position % 2 == 0)
            {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }
            else
            {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            messageText = (TextView) result.findViewById(R.id.message_text);
            messageText.setText(getItem(position));
            return result;
        }
    }
}
