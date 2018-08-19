package com.example.dowkk.apply11streetapi;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText keywordEdt;
    private Button searchBtn;
    private List<Product> productList;
    private Button sendbt;  //for test
  //  private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();//for test
    //private DatabaseReference databaseReference = firebaseDatabase.getReference();//for test

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    private ListView listView;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keywordEdt = (EditText)findViewById(R.id.main_keyword_edt);
        searchBtn = (Button)findViewById(R.id.main_search_btn);
        productList = new ArrayList<Product>();
        adapter = new ProductAdapter(this, R.layout.list_product_item, productList);
        listView = (ListView) findViewById(R.id.main_listView);

        listView.setAdapter(adapter);
        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String keyword = keywordEdt.getText().toString();
                ProductSearchService service = new ProductSearchService(keyword);
                ProductSearchThread thread = new ProductSearchThread(service, handler);
                thread.start();
            }
        });

        sendbt = (Button) findViewById(R.id.button2);//for test
        sendbt.setOnClickListener(new Button.OnClickListener(){ //for test
                    public void onClick(View v) {   //for test
                        databaseReference.child("message").push().setValue("2");//for test
                    }//for test
                });//for test
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
                    productList.clear();
                    productList.addAll((List<Product>) msg.obj);
                    adapter.notifyDataSetChanged();
                }
//                arg2이 20이면 검색했던 결과에 대해 추가 아이템을 요청한걸로
                if(msg.arg2==20){

                }
            }
        }
    };
}