package app.clutch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.StrictMode;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.SliderLayout;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.HttpUrl;

public class StoreItemActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Context context;
    ImageView storeItemImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_item);

        Intent myIntent = getIntent(); // gets the previously created intent
        String firstKeyName = myIntent.getStringExtra("itemName");

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


        storeItemImageView = findViewById(R.id.storeItemImageView);

        Glide.with(context)
                .asBitmap()
                .load(myIntent.getStringExtra("itemImageURL"))
                .into(storeItemImageView);



    }



//System.out.println(" tx sewn satatus: " + "succeed");

}






