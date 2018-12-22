package com.example.myapplication;

import android.app.Person;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

import java.util.Locale;

public class cust_req extends AppCompatActivity {
    TextView name;
    Spinner sp;
    SQLiteDatabase db;
    Button req,stat;
    String acno,amou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_req);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        name= findViewById(R.id.textView51);
        name.setText(globalvariabel.GetUsername().toString());
        sp=(Spinner)findViewById(R.id.spinner);
        req = findViewById(R.id.button19);
        acno="Waiting For Bank To Accept";
        amou="Waiting For Bank To Accept";
        db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists ureq(email varchar,bank varchar,acno varchar,amou varchar);");
        req.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Cursor cc=db.rawQuery("select * from ureq where email='"+name.getText()+"' and bank='"+sp.getSelectedItem()+"'", null);
                if ((cc.moveToFirst()))
                {
                    Toast.makeText(getApplicationContext(), "your Request Already Sent", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.execSQL("insert into ureq values('" + name.getText() + "','" + sp.getSelectedItem()+ "','"+acno+"','"+amou+"');");
                Toast.makeText(getApplicationContext(), "Request Sent Successfully!", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        stat = findViewById(R.id.button20);
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=db.rawQuery("SELECT * FROM ureq WHERE email='"+name.getText()+"'", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Bank Name: "+c.getString(1)+"\n");
                    buffer.append("Account No: "+c.getString(2)+"\n");
                    buffer.append("Balance: "+c.getString(3)+"\n\n");
                }
                showMessage("Bank Details:", buffer.toString());
            }
            });
      }

      public void showMessage(String title,String message) {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
