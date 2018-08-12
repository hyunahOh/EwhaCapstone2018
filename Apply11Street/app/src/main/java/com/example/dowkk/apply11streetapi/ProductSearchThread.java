package com.example.dowkk.apply11streetapi;

import android.os.Handler;
import android.os.Message;

import java.util.List;

public class ProductSearchThread extends Thread {
    private ProductSearchService service;
    private Handler handler;

    public ProductSearchThread(ProductSearchService service, Handler handler){
        this.service = service;
        this.handler = handler;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        // service의 search메소드를 수행하고 결과를 핸들러를 통해 메인에게 전달

        List<Product> data = service.search();
        Message msg = handler.obtainMessage();
        msg.what = 1;
        msg.obj = data;
//        Log.d("YJ", data.get(0).getProductName());
        if (service.getCurrentSkip() == 1)
            msg.arg1 = 10;
        else
            msg.arg2 = 20;
        handler.sendMessage(msg);
    }
}
