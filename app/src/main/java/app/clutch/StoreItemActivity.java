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
import okhttp3.RequestBody;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.MediaType;

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


        /*
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

        */


        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back);
        upArrow.setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);




        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);





        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://13.229.138.18:8080/clutch/app/goods/getGoodsList").newBuilder();
        urlBuilder.addQueryParameter("goodId", "2009147G6G0K98X4");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("lang", "en")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(" Response: " + response.body().string());
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }

/*
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://13.229.138.18:8080/clutch/app/goods/getUserAddrList").newBuilder();
        //urlBuilder.addQueryParameter("page", "1");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("lang", "en")
                .addHeader("token", "en")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(" Response: " + response.body().string());
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }



         */


/*
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // create your json here
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("province", "Hubei");
            jsonObject.put("city", "Wuhan");
            jsonObject.put("district", "Hongshan");
            jsonObject.put("detail", "Honghu Garden 11");
            jsonObject.put("receiver", "Burak");
            jsonObject.put("isDefault", "1");
            jsonObject.put("mobile", "15527185799");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url("http://13.229.138.18:8080/clutch/app/user/addOrUpdateUserAddr")
                .addHeader("lang","en")
                .addHeader("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjAwMzM5OTE2LCJleHAiOjE2MDI5MzE5MTZ9.fl8kWQ8oeu5VuD_k0Nc4l6kNUA9y_Xz_eO-GqvIRGfpp56wqm4pG7TQqbAOUDveCIwXI4NFQ4Gmam_f-CfSlBg")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("xResponse: " + response.body().string());
        } catch (IOException e) {
            System.out.println("xError");
            e.printStackTrace();
        }

*/
/*

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // create your json here
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", "burak@gatepay.co");
            jsonObject.put("emailCodeType", "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url("http://13.229.138.18:8080/clutch/app/user/getEmailCode")
                .addHeader("lang","en")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("xResponse: " + response.body().string());
        } catch (IOException e) {
            System.out.println("xError");
            e.printStackTrace();
        }


 */
/*


        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // create your json here
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", "burak@credentialsapp.org");
            jsonObject.put("emailCodeType", "1");
            jsonObject.put("password", "e10adc3949ba59abbe56e057f20f883e");
            jsonObject.put("code", "516669");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url("http://13.229.138.18:8080/clutch/app/user/registerByEmail")
                .addHeader("lang","en")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("xResponse: " + response.body().string());
        } catch (IOException e) {
            System.out.println("xError");
            e.printStackTrace();
        }

*/

        storeItemImageView = findViewById(R.id.storeItemImageView);

        Glide.with(context)
                .asBitmap()
                .load(myIntent.getStringExtra("itemImageURL"))
                .into(storeItemImageView);



    }



//System.out.println(" tx sewn satatus: " + "succeed");

}






