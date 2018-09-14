
package com.example.dowkk.apply11streetapi.search;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dowkk.apply11streetapi.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";

    private ArrayList<Product> mProducts = new ArrayList<>();

    private EditText keywordEdt;
    private Button searchBtn;
    private RecyclerViewAdapter adapter;

    ProductSearchService service = new ProductSearchService();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d(TAG, "onCreate: started");

        keywordEdt = (EditText)findViewById(R.id.main_keyword_edt);
        searchBtn = (Button)findViewById(R.id.main_search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String keyword = keywordEdt.getText().toString();
                new SearchAsyncTask().execute(keyword);
                }
        });
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        adapter = new RecyclerViewAdapter(this, mProducts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    class SearchAsyncTask extends AsyncTask<String, Void, List<Product>> {

        @Override
        protected List<Product> doInBackground(String... strings) {
            String keyword = strings[0];
            service.setKeyword(keyword);
            service.setContext(getApplicationContext());
            List<Product> data = service.search();

            return data;
        }

        @Override
        protected void onPostExecute(List<Product> obj) {
            super.onPostExecute(obj);

            mProducts.addAll(obj);
            initRecyclerView();
        }
    }

}

