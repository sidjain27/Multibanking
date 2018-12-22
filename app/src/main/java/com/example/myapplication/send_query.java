package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class send_query extends Activity {
    TextView name;
    EditText q;
    Button sub,stat;
    SQLiteDatabase db;
    Spinner sp;
    String qstat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_query);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        name= findViewById(R.id.textView53);
        name.setText(globalvariabel.GetUsername().toString());
        q= findViewById(R.id.editText20);
        sp=(Spinner)findViewById(R.id.spinner2);
        sub = findViewById(R.id.button21);
        qstat="Unsolved...";
        db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists uquery(email varchar,bank varchar,quer varchar,status varchar);");
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("insert into uquery values('" + name.getText() + "','" + sp.getSelectedItem()+ "','"+q.getText()+"','"+qstat+"');");
                Toast.makeText(getApplicationContext(), "Request Sent Successfully!", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        stat = findViewById(R.id.button22);
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=db.rawQuery("SELECT * FROM uquery WHERE email='"+name.getText()+"'", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Bank Name: "+c.getString(1)+"\n");
                    buffer.append("Query : "+c.getString(2)+"\n");
                    buffer.append("Query Status: "+c.getString(3)+"");
                }
                showMessage("Query Details:", buffer.toString());

            }
        });
    }

    public void showMessage(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
