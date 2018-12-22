package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.GlobalClass;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.admin;
import com.example.myapplication.cust_home;

public class LoginActivity2 extends Activity {
    EditText em,pas;
    Button Log;
    String u;
    String p;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        setContentView(R.layout.activity_login2);
        em = (EditText) findViewById(R.id.editText15);
        pas= (EditText) findViewById(R.id.editText16);
        Log=(Button) findViewById(R.id.button2);
        Log.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(em.getText().toString().equals("")||pas.getText().toString().equals("")){

                    Toast.makeText(LoginActivity2.this, "Please enter the fields..!", Toast.LENGTH_LONG).show();
                }else{
                    u = em.getText().toString();
                    p = pas.getText().toString();
                    try{
                        db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE,null);
                    }catch(Exception exception){
                        exception.printStackTrace();
                    }try{

                        if(em.getText().toString().equals("admin")&& pas.getText().toString().equals("admin")){
                            Toast.makeText(LoginActivity2.this, "Welcome Admin..."  + u , Toast.LENGTH_LONG).show();
                            globalvariabel.Setusername(em.getText().toString().trim());
                            globalvariabel.Setpassword(pas.getText().toString().trim());
                            Intent a = new Intent(LoginActivity2.this, admin.class);
                            startActivity(a);
                            clear();
                            return;
                        }

                        Cursor cc = db.rawQuery("select * from creg where email='"+u+"' and pass='"+p+"'", null);
                        // customer Login
                        if(cc.moveToFirst())
                        {String temp="";
                            if (cc != null)
                            {
                                if(cc.getCount() > 0)
                                {
                                    //return true;
                                    scan g=new scan();
                                    g.execute();
                                    Toast.makeText(LoginActivity2.this, "Welcome To Customer Home!!"  + u , Toast.LENGTH_LONG).show();
                                    globalvariabel.Setusername(em.getText().toString().trim());
                                    globalvariabel.Setpassword(pas.getText().toString().trim());
                                    Intent i = new Intent(LoginActivity2.this,cust_home.class);
                                    startActivity(i);

                                }
                                else
                                {
                                    Toast.makeText(LoginActivity2.this, "Login Failed1!Please Try Again!", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(LoginActivity2.this, "Login Failed2!Please Try Again!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity2.this, "User not Registered!", Toast.LENGTH_SHORT).show();
                        }


                        Cursor cc1 = db.rawQuery("select * from breg where email='"+u+"' and pass='"+p+"'", null);

                        // customer Login
                        if(cc1.moveToFirst())
                        {String temp="";
                            if (cc1 != null)
                            {
                                if(cc1.getCount() > 0)
                                {
                                    //return true;
                                    scan g=new scan();
                                    g.execute();
                                    Toast.makeText(LoginActivity2.this, "Welcome To Bank Home!!"  + u , Toast.LENGTH_LONG).show();
                                    globalvariabel.Setusername(em.getText().toString().trim());
                                    globalvariabel.Setpassword(pas.getText().toString().trim());
                                    Intent i = new Intent(LoginActivity2.this,bank_home.class);
                                    startActivity(i);

                                }
                                else
                                {
                                    Toast.makeText(LoginActivity2.this, "Login Failed4!Please Try Again!", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(LoginActivity2.this, "Login Failed5!Please Try Again!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity2.this, "User not Registered!", Toast.LENGTH_SHORT).show();
                        }


                    }catch(Exception exception){
                        exception.printStackTrace();
                    }
                }

            }


        });

    }
    public class scan extends AsyncTask<String, String, String>{

        private ProgressDialog pd;

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(LoginActivity2.this);
            pd.setTitle("Please Wait");
            pd.setMessage("Logging....");
            pd.setMax(10);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    public void clear()
    {
        em.setText("");
        pas.setText("");
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent i=new Intent(LoginActivity2.this,MainActivity.class);
        startActivity(i);
    }
}
