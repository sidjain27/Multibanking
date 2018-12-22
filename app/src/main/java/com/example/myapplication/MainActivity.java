package com.example.myapplication;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import java.util.logging.Handler;
        import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    Button cr,br,login;
    boolean mPressedOnce=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cr=findViewById(R.id.button3);
        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,customer_reg.class));
            }
        });
        br=findViewById(R.id.button4);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,bank_reg.class));
            }
        });
        login=findViewById(R.id.button5);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity2.class));
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        if(mPressedOnce)
        {
            super.onBackPressed();
        }
        this.mPressedOnce=true;

        Toast.makeText(this,"Press again to exit",Toast.LENGTH_SHORT).show();
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPressedOnce=false;
            }
        }, 4000);
    }
}