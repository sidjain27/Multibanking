package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class admin_req1 extends AppCompatActivity {
    TextView email,bank,acno;
    EditText amt;
    SQLiteDatabase db;
    Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_req1);
        Bundle bundle=getIntent().getExtras();
        email=(TextView) findViewById(R.id.textView61);
        email.setText(bundle.getCharSequence("email"));
        bank=(TextView) findViewById(R.id.textView64);
        bank.setText(bundle.getCharSequence("bank"));
        acno=(TextView) findViewById(R.id.textView66);
        acno.setText(generateACNO());
        amt=(EditText)findViewById(R.id.editText18);
        amt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
                db.execSQL("create table if not exists ureq(email varchar,bank varchar,acno varchar,amou varchar);");
                db.execSQL("UPDATE ureq SET acno='"+acno.getText()+"',amou='"+amt.getText()+"' WHERE email='"+email.getText()+"' and bank='"+bank.getText()+"'");
                Toast.makeText(getApplicationContext(), "Record Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String generateACNO()
    {
        int randomacno=((int)(Math.random()*900000)+100000)*100;
        return String.valueOf(randomacno);
    }
}
