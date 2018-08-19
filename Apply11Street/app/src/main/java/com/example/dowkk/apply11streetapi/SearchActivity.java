package com.example.dowkk.apply11streetapi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";

    private ArrayList<Product> mProducts = new ArrayList<>();

    private EditText keywordEdt;
    private Button searchBtn;
    private RecyclerViewAdapter adapter;

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
                ProductSearchService service = new ProductSearchService(keyword);
                ProductSearchThread thread = new ProductSearchThread(service, handler);
                thread.start();
                initRecyclerView();
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

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @SuppressLint("WrongConstant")
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            Log.d("test", String.valueOf(msg));

            if(msg.what ==1 )
            {
                //arg1이 10이면 처음 검색에 대한 결과를 갖다 준걸로
                if(msg.arg1==10)
                {
                    String result = "";
                    List<Product> data = (List<Product>)msg.obj;
                    for(Product p : data)
                        result += p.getProductName() +"\n";
                    //Toast.makeText(MainActivity.this, result, 0).show();
                    //Toast.makeText(MainActivity.this, "dd", 0).show();
                    mProducts.clear();
                    mProducts.addAll((List<Product>) msg.obj);
                    adapter.notifyDataSetChanged();
                }
//                arg2이 20이면 검색했던 결과에 대해 추가 아이템을 요청한걸로
                if(msg.arg2==20){

                }
            }
        }
    };
}
