package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Send_money extends AppCompatActivity {
    TextView uid,acno,bal;
    EditText ac,name,ifsc,amou;
    SQLiteDatabase db;
    Button trns,get;
    Spinner sp;
    double av,am,deb;
    String [] bank = {
            "SBI",
            "PNB",
            "ICICI",
            "HDFC"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        uid= findViewById(R.id.textView56);
        uid.setText(globalvariabel.GetUsername().toString());
        ac=(EditText)findViewById(R.id.editText10);
        name=(EditText)findViewById(R.id.editText17);
        ifsc=(EditText)findViewById(R.id.editText11);
        amou=(EditText)findViewById(R.id.editText14);
        trns=(Button)findViewById(R.id.button17);
        sp=(Spinner)findViewById(R.id.spinner4);
        ArrayAdapter<String> myad = new ArrayAdapter<String>(Send_money.this, android.R.layout.simple_spinner_item,bank);
        sp.setAdapter(myad);
        get=(Button)findViewById(R.id.button24);
        acno=(TextView)findViewById(R.id.textView57);
        bal=(TextView)findViewById(R.id.textView58);
        db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists ureq(email varchar,bank varchar,acno varchar,amou varchar);");
        db.execSQL("create table if not exists utran(Uid varchar,bank varchar,acno varchar,amou varchar,ac2no varchar,toname varchar,ifsc varchar,payamount varchar);");
      //  uid.setText(globalvariabel.GetUsername().toString());
        get.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Cursor c = db.rawQuery("select * from ureq where email= '"+uid.getText()+"' and bank='"+sp.getSelectedItem()+"' ", null);
                if(c.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(), "No Records Found!", Toast.LENGTH_SHORT).show();
                    return;
                }
                while(c.moveToNext())
                {
                    acno.setText(c.getString(2));
                    bal.setText(c.getString(3));
                }
            }
        });
        trns.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                av=Double.parseDouble(bal.getText().toString());
                am=Double.parseDouble(amou.getText().toString());
                deb=av-am;


                if(av>am)
                {
                    db.execSQL("insert into utran values('" + uid.getText() + "','" + sp.getSelectedItem() + "','"+acno.getText() +"','"+bal.getText()+"','"+ac.getText()+"','"+name.getText()+"','"+ifsc.getText()+"','"+amou.getText()+"');");
                    db.execSQL("UPDATE ureq SET amou='"+deb+"' WHERE email='"+uid.getText()+"' and acno='"+acno.getText()+"'");
                    Toast.makeText(getApplicationContext(), "Money Sent!", Toast.LENGTH_SHORT).show();
                }



                Toast.makeText(getApplicationContext(), "Transfer Request Sent!", Toast.LENGTH_SHORT).show();
                Intent a =(new Intent(Send_money.this,cust_home.class));
                startActivity(a);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_send_money, menu);
        return true;
    }
}
