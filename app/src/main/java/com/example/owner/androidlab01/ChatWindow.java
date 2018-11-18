package com.example.owner.androidlab01;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ChatWindow extends Activity {
    //ChatWindow variables
    ListView listView;
    EditText textWindow;
    Button sendButton;
    Boolean FrameExists;
    private final int DELETE_REQUEST = 10;
    private Cursor cursor;
    public int position;


    //ChatDatabaseHelper variables
    private static final String ACTIVITY_NAME = "ChatWindowActivity" ;
    ArrayList<String> chatMessages = new ArrayList<>();
    public ChatDatabaseHelper dbHelper ;
    public SQLiteDatabase db ;
    private ChatAdapter messageAdapter;

    //ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);



        //DB initialized and stored
        dbHelper = new ChatDatabaseHelper(this);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException s) {
            new Exception("Error with DB Open");
        }
        final ContentValues newROW = new ContentValues();


        //ChatWindow initialize
        listView = (ListView)findViewById(R.id._chatView);
        textWindow = (EditText)findViewById(R.id.chatBoxx);
        sendButton = (Button) findViewById(R.id.sendButton);

        messageAdapter = new ChatAdapter( this );
        listView.setAdapter (messageAdapter);

        //CURSOR
        cursor = db.query(ChatDatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast() ){
            chatMessages.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }
        Log.i(ACTIVITY_NAME, "Cursor’s column count = "+ cursor.getColumnCount() );
        for (int i = 0; i < cursor.getColumnCount(); i++){
            Log.i(ACTIVITY_NAME, "SQL column rows: " + cursor.getColumnName(i));
        }

        //FRAME
        FrameExists = findViewById(R.id.FMdetails) != null;
        //ListView OnItem - get message and ID
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String msg = cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
                Bundle bundle = new Bundle();
                bundle.putLong("id",id);
                bundle.putString("msg", msg);
                bundle.putInt("position",position);

                //PHONE
                if(!FrameExists){
                    Intent intent = new Intent(ChatWindow.this, MessageDetailsActivity.class);
                    intent.putExtra("msgDetails", bundle);
                    startActivityForResult(intent,DELETE_REQUEST);

                //TABLET
                }else{
                    MessageFragment messageFragment = new MessageFragment();
                    messageFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.FMdetails,messageFragment).commit();
                }

            }
        });

        //ListView OnClick
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chat = textWindow.getText().toString();
                chatMessages.add(chat);
                messageAdapter.notifyDataSetChanged();
                textWindow.setText(" ");
                newROW.put(ChatDatabaseHelper.KEY_MESSAGE, chat);
                db.insert(ChatDatabaseHelper.TABLE_NAME,"", newROW);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == DELETE_REQUEST){
            if(resultCode == Activity.RESULT_OK){

                Bundle deletes = data.getExtras();
                long id = deletes.getLong("id");
                int position = deletes.getInt("position");
                deleteMessage(id,position);
            }
        }
    }
    protected void deleteMessage(long id, int position){
        chatMessages.remove(position);
        db.delete(ChatDatabaseHelper.TABLE_NAME, ChatDatabaseHelper.KEY_ID + "=" + id, null);
        messageAdapter.notifyDataSetChanged();
    }

    private class ChatAdapter extends ArrayAdapter<String>{

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

        //Lab07 section 6
        @Override
        public long getItemId(int position) {

            if (cursor == null)
                throw new NullPointerException("ERROR: cursor is null");

                // refresh the cursors
                cursor = db.rawQuery("SELECT * FROM " + ChatDatabaseHelper.TABLE_NAME, null);
                cursor.moveToPosition(position);
                return cursor.getLong(cursor.getColumnIndex(ChatDatabaseHelper.KEY_ID));
            }

        //@NonNull
        public View getView(int position, View convertView, /*@NonNull*/ViewGroup parent)
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
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        db.close();
        dbHelper.close();
    }
}
