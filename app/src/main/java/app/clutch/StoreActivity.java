package app.clutch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Context context;
    public RecyclerView storeRecView;
    StoreRecViewAdapter adapter;
    ArrayList<StoreItem> arrayList;

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





        storeRecView = findViewById(R.id.storeRecView);


        arrayList = new ArrayList<StoreItem>();



        arrayList.add(new StoreItem("asasdsd","https://i.hizliresim.com/QvKhrV.png"));
        arrayList.add(new StoreItem("asasdsd","https://i.hizliresim.com/QvKhrV.png"));
        arrayList.add(new StoreItem("asasdsd","https://i.hizliresim.com/QvKhrV.png"));
        arrayList.add(new StoreItem("asasdsd","https://i.hizliresim.com/QvKhrV.png"));


        resetAdapter();




    }

    public void resetAdapter(){
        adapter = new StoreRecViewAdapter(context);
        adapter.setCollectibles(arrayList);
        storeRecView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;//put your condition here
            }
        });
        storeRecView.setLayoutManager(layoutManager);


        //storeRecView.setLayoutManager(new GridLayoutManager(context, 2));
    }

}