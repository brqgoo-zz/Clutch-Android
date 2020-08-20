package app.clutch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff;
import android.widget.Toast;
import android.os.StrictMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.net.URLConnection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.HttpUrl;

public class StoreItemActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Context context;
    SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        Intent myIntent = getIntent(); // gets the previously created intent
        String firstKeyName = myIntent.getStringExtra("itemID");

        setTitle(firstKeyName);
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
                        startActivity(new Intent(context, StoreActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(context, InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back);
        upArrow.setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        sliderView = findViewById(R.id.imageSlider);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://13.229.138.18:8080/clutch/app/goods/getGoodsList").newBuilder();
        urlBuilder.addQueryParameter("page", "1");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                //.addHeader("page", "1")
                .addHeader("lang", "en")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(" Response Is: " + response.body().string());
        } catch (IOException e) {
            System.out.println(" error: " + "error");
            e.printStackTrace();
        }


    }

//System.out.println(" tx sewn satatus: " + "succeed");

}






