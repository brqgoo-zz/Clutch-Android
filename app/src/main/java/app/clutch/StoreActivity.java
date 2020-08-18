package app.clutch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StoreActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Context context;
    WebView storeWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        setTitle("Store");

        context = this;

        bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.store);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(context, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.store:
                        return true;
                    case R.id.info:
                        startActivity(new Intent(context, InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        storeWebView = findViewById(R.id.storeWebView);
        storeWebView.setWebViewClient(new WebViewClient());
        storeWebView.loadUrl("https://shop.warriors.com/golden-state-warriors-men/t-14145130+ga-67+z-978556-2897172570");

        WebSettings webSettings = storeWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }

    @Override
    public void onBackPressed() {
        if (storeWebView.canGoBack()){
            storeWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}