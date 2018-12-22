package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class bank_trans extends AppCompatActivity {
    TextView uid;
    ImageView out;
    EditText tl;
    SQLiteDatabase db;
    ArrayList<String> list1;
    ArrayAdapter adapter;
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_trans);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        uid=(TextView)findViewById(R.id.textView75);
        uid.setText(globalvariabel.GetUsername().toString());
        out=(ImageView)findViewById(R.id.imageView10);
        tl=(EditText)findViewById(R.id.search2);
        db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists utran(Uid varchar,bank varchar,acno varchar,amou varchar,ac2no varchar,toname varchar,ifsc varchar,payamount varchar);");
        l = (ListView) findViewById(R.id.listView2);
        final ArrayList<String> list = new ArrayList<String>();
        list1 = new ArrayList<String>();
        Cursor res=db.rawQuery("SELECT * FROM utran", null);
        if(res.getCount()!=0)
        {
            while (res.moveToNext())
            {
                list.add("Sender:   "+res.getString(0)+"\nBank:   "+res.getString(1)+"\n"+"A/c No:  "+res.getString(2)+"\n"+"Balance:  "+res.getString(3)+"\n"+"To A/c no: "+res.getString(4)+"\n"+"To Name:  "+res.getString(5)+"\n"+"To IFSC: "+res.getString(6)+"\n"+"Amount:  "+res.getString(7));
                list1.add(res.getString(1));
                // email=res.getString(0);
                // bank=res.getString(1);
            }
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        l.setAdapter(adapter);


        // (close)

        /// search (start)

        tl.addTextChangedListener(new TextWatcher(){

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                bank_trans.this.adapter.getFilter().filter(s);

            }

        });

    }
}
