package com.example.myapplication;
import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class bank_query extends Activity {
    ImageView out;
    TextView aaa;
    SQLiteDatabase db;
    ListView l;
    EditText t1;
    ArrayList<String> list1;
    ArrayAdapter adapter;
    String email,bank,query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_query);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        aaa=(TextView)findViewById(R.id.textView62);
        aaa.setText(globalvariabel.GetUsername().toString());
        out=(ImageView)findViewById(R.id.imageView10);
        t1=(EditText)findViewById(R.id.search);
        db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists uquery(email varchar,bank varchar,quer varchar,status varchar);");
        l = (ListView) findViewById(R.id.listView1);
        final ArrayList<String> list = new ArrayList<String>();
        list1 = new ArrayList<String>();
        Cursor res=db.rawQuery("SELECT * FROM uquery", null);
        if(res.getCount()!=0)
        {
            while (res.moveToNext())
            {
                list.add("Email:   "+res.getString(0)+"\nBank:   "+res.getString(1)+"\n"+"Query :   "+res.getString(2)+"\n"+"Remarks :   "+res.getString(3));
                list1.add(res.getString(1));
                email=res.getString(0);
                bank=res.getString(1);
                query=res.getString(2);
            }
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        l.setAdapter(adapter);


        // (close)

        /// search (start)

        t1.addTextChangedListener(new TextWatcher(){

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
                bank_query.this.adapter.getFilter().filter(s);

            }

        });

        // (close)

        // alert dialog

        l.setAdapter(adapter);
        l.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {



                // two buttons (start)
                // TODO Auto-generated method stub
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(bank_query.this);

                // Setting Dialog Title
                alertDialog.setTitle("Confirm Action...");

                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want confirm this?");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.ic_launcher_background);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        // Write your code here to invoke YES event
                        Bundle bundle=new Bundle();
                        Intent xx=new Intent(bank_query.this,bank_query1.class);
                        bundle.putString("email",email);
                        bundle.putString("bank",bank);
                        bundle.putString("query",query);
                        xx.putExtras(bundle);
                        startActivity(xx);
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        Intent b=new Intent(getApplicationContext(),admin.class);
                        startActivity(b);
                        Toast.makeText(getApplicationContext(), "Refreshed Successfully", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                // Showing Alert Message
                alertDialog.show();

                // (close)
            }
        });



        //
        ///logout
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
