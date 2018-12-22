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

public class bank_query1 extends AppCompatActivity {
    TextView email,bank,query;
    EditText rem;
    SQLiteDatabase db;
    Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_query1);
        Bundle bundle=getIntent().getExtras();
        email=(TextView) findViewById(R.id.textView61);
        email.setText(bundle.getCharSequence("email"));
        bank=(TextView) findViewById(R.id.textView64);
        bank.setText(bundle.getCharSequence("bank"));
        query=(TextView) findViewById(R.id.textView66);
        query.setText(bundle.getCharSequence("query"));
        rem=(EditText)findViewById(R.id.editText18);
        rem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
                db.execSQL("create table if not exists uquery(email varchar,bank varchar,quer varchar,status varchar);");
                db.execSQL("UPDATE uquery SET status='"+rem.getText()+"' WHERE email='"+email.getText()+"' and bank='"+bank.getText()+"'");
                Toast.makeText(getApplicationContext(), "Query Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
