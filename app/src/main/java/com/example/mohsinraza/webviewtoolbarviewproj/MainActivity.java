package com.example.mohsinraza.webviewtoolbarviewproj;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView mwebview;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        mwebview=(WebView)findViewById(R.id.webview);
        WebSettings webSettings = mwebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        Intent intent = getIntent();
        String strUrlAdd=intent.getStringExtra("url");
        if(strUrlAdd.contains("https://")) {
            mwebview.loadUrl(strUrlAdd);
        }
        else{
            mwebview.loadUrl("https://"+strUrlAdd);
        }
        mwebview.setWebViewClient(new myWebClient());
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        }

    private void setToolbar() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PGC App");
//        toolbar.setSubtitle("www.pgc.edu.pk");
        toolbar.setLogo(R.drawable.path1);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //app icon in action bar clicked; goto parent activity.
               this.finish();
                //Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivityForResult(myIntent, 0);
                break;
            case R.id.action_share:
                Toast.makeText(getApplicationContext(),"shared item called",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_rate_us:
                Toast.makeText(getApplicationContext(),"Rate Us Item called",Toast.LENGTH_LONG).show();

                break;
            case R.id.action_log_out:
                Toast.makeText(getApplicationContext(),"Log Out Item called",Toast.LENGTH_LONG).show();

                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public  class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);

        }
    }
}




