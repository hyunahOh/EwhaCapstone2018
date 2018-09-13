package com.example.dowkk.apply11streetapi.search;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.dowkk.apply11streetapi.R;

public class InfoActivity extends AppCompatActivity {

    private static String productCode = "1937887300";
    private TextView txtShipFee;
    InfoService service = new InfoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        if(intent!=null){
            productCode = intent.getStringExtra("productCode");
        }
        txtShipFee = findViewById(R.id.txtShipFee);
        new InfoAsyncTask().execute(productCode);
    }

    class InfoAsyncTask extends AsyncTask<String, Void, Product> {

        @Override
        protected Product doInBackground(String... strings) {
            String productCode = strings[0];
            service.setProductCode(productCode);

            Product p = service.getInfo();
            Log.e("huh", p.getProductName());

            return p;
        }
        @Override
        protected void onPostExecute(Product product) {
            Log.e("hihihi", product.getShipFee());
            txtShipFee.setText(product.getShipFee());
            super.onPostExecute(product);
        }
    }
}
