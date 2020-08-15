package app.clutch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import java.io.IOException;
import android.os.StrictMode;
import org.web3j.crypto.WalletUtils;
import org.web3j.crypto.Credentials;
import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Provider;
import java.security.Security;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import java.math.BigInteger;
import java.util.ArrayList;

import org.web3j.utils.Convert.Unit;
import org.web3j.utils.Convert;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.utils.Numeric;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

public class MainActivity extends AppCompatActivity {

    private RecyclerView collectiblesRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collectiblesRecView = findViewById(R.id.collectiblesRecView);


        ArrayList<Collectible> arrayList = new ArrayList<Collectible>();
        arrayList.add(new Collectible("asdsdsa","hhhtp asddsasddas"));
        arrayList.add(new Collectible("asdsdsa1","hhhtp asddsasddas1"));
        arrayList.add(new Collectible("asdsdsa2","hhhtp asddsasddas2"));

        CollectiblesRecViewAdapter adapter = new CollectiblesRecViewAdapter(this);
        adapter.setCollectibles(arrayList);

        collectiblesRecView.setAdapter(adapter);
        collectiblesRecView.setLayoutManager(new GridLayoutManager(this, 2));

    }
}