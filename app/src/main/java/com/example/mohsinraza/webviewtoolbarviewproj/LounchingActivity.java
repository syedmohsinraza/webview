package com.example.mohsinraza.webviewtoolbarviewproj;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LounchingActivity extends AppCompatActivity {
private Button btnsubmit;
private EditText etxturl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lounching);
        btnsubmit=(Button)findViewById(R.id.btnsubmit);
        etxturl=(EditText)findViewById(R.id.url_text);
        final Intent intent=new Intent(this,MainActivity.class);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();

                final String strUrl=etxturl.getText().toString();
                 intent.putExtra("url",strUrl);
                 startActivity(intent);
            }
        });
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        } else {
            return false;
        }
    }
    public void checkConnection(){
        if(isOnline()){
            Toast.makeText(LounchingActivity.this, "You are connected to Internet", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(LounchingActivity.this, "You are not connected to Internet", Toast.LENGTH_LONG).show();
        }
    }
}
