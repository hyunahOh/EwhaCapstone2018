package com.example.dowkk.detailedinfo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProductInfoActivity extends AppCompatActivity {

    private static String productCode = "1937887300";
    ProductInfoService service = new ProductInfoService();
    TextView txtShipFree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        txtShipFree = findViewById(R.id.txtShipFee);
        new SearchAsyncTask().execute(productCode);
    }


    class SearchAsyncTask extends AsyncTask<String, Void, Product> {

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
            txtShipFree.setText(product.getShipFee());
            super.onPostExecute(product);
        }

    }
}
