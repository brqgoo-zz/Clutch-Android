package app.clutch;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.io.ByteArrayInputStream;
import com.bumptech.glide.load.DataSource;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public RecyclerView collectiblesRecView;
    CollectiblesRecViewAdapter adapter;
    ArrayList<Collectible> arrayList;
    FileOutputStream fOut;
    FileInputStream fin;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collectiblesRecView = findViewById(R.id.collectiblesRecView);
        context = this;

        arrayList = new ArrayList<Collectible>();

        Button but = findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliccked();
            }
        });

        fetchCollectibles();
        resetAdapter();
    }

    public void cliccked (){
        Toast.makeText(context, "wasdd", Toast.LENGTH_SHORT).show();

        arrayList.add(new Collectible("sddsaddsad","https://w7.pngwing.com/pngs/910/638/png-transparent-logo-nba-tv-sport-nba.png"));
        saveCollectible();
        resetAdapter();

    }

    public void resetAdapter(){
        adapter = new CollectiblesRecViewAdapter(context);
        adapter.setCollectibles(arrayList);
        collectiblesRecView.setAdapter(adapter);
        collectiblesRecView.setLayoutManager(new GridLayoutManager(context, 2));
    }

    public void fetchCollectibles(){

        arrayList = new ArrayList<Collectible>();

        ////Reading Collectibles ArrayList as ByteArray, internally
        try {
            fin = openFileInput("collectibles");
            int i = 0;
            char c;
            byte[] bs = new byte[0];
            try {
                if(fin.available() > 0){
                    bs = new byte[fin.available()];
                    fin.read(bs);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            ////Reading Collectibles ArrayList from ByteArray
            ByteArrayInputStream bais = new ByteArrayInputStream(bs);
            DataInputStream in = new DataInputStream(bais);
            while (true) {
                try {
                    if (!(in.available() > 0)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String element = null;
                try {
                    element = in.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                arrayList.add(new Collectible(element.split("name='")[1].split("'")[0],element.split("imageUrl='")[1].split("'")[0]));

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveCollectible (){

        System.out.println(" addcolelctiblebas ");
        //Collectibles ArrayList
        //Collectibles ArrayList as ByteArray
        byte[] CollectiblesArrayListBytes = null;

        //Converting Collectibles ArrayList to ByteArray
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        for (Collectible element : arrayList) {
            try {
                out.writeUTF(String.valueOf(element));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        CollectiblesArrayListBytes = baos.toByteArray();

        //Saving Collectibles ArrayList as ByteArray, internally
        try {
            fOut = openFileOutput("collectibles",Context.MODE_PRIVATE);
            fOut.write(CollectiblesArrayListBytes);
            fOut.close();
            System.out.println(" addcolelctiblesucc ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" addcolelctiblesuc 1 s " + arrayList.size());
    }


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }


    /*

    private void saveImage(Bitmap image, File storageDir, String imageFileName) {

        boolean successDirCreated = false;
        if (!storageDir.exists()) {
            successDirCreated = storageDir.mkdir();
        }
        if (successDirCreated) {
            File imageFile = new File(storageDir, imageFileName);
            String savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.close();
                Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Error while saving image!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }else{
            Toast.makeText(this, "Failed to make folder!", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean verifyPermissions() {

        // This will return the current Status
        int permissionExternalMemory = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {

            String[] STORAGE_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            // If permission not granted then ask for permission real time.
            ActivityCompat.requestPermissions(this, STORAGE_PERMISSIONS, 1);
            return false;
        }

        return true;

    }

     */
}