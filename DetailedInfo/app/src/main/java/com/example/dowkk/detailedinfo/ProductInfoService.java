package com.example.dowkk.detailedinfo;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

//상세정보 페이지 구현

public class ProductInfoService {
    // 요청할 URL
    private static final String URL = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=d74ff69646da36ffd552514f1d7de3a6&apiCode=ProductInfo&productCode=";

    //현재 페이지를 알기 위한 상태값
    private int currentSkip=1;

    private String productCode;

    public ProductInfoService() {}

    public ProductInfoService(String productCode){
        this.productCode = productCode;
    }

    public Product getInfo() {
        Product p = null;
        try {
            URL url;
            url = new URL(URL  + URLEncoder.encode(productCode, "EUC-KR"));

            URLConnection urlConn = url.openConnection();

            // xml파서객체만들고
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            // 요청에 대한 응답 결과를 파서에 세팅
            parser.setInput(new InputStreamReader(urlConn.getInputStream(),"EUC-KR"));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.END_DOCUMENT: // 문서의 끝
                        break;
                    case XmlPullParser.START_DOCUMENT:
                        //list = new ArrayList<Product>();
                        break;
                    case XmlPullParser.END_TAG: {
                        String tag = parser.getName();
                        if (tag.equals("Product")) {
                            return p;
                        }
                    }
                    case XmlPullParser.START_TAG: {
                        String tag = parser.getName();
                        switch (tag) {
                            case "Product":
                                p = new Product();
                                break;
                            case "ProductCode":
                                if(p!=null){
                                    p.setProductCode(parser.nextText());
                                    Log.e("ProductCode", p.getProductCode());
                                }
                                break;
                            case "ProductName":
                                if(p!=null) {
                                    p.setProductName(parser.nextText());
                                    Log.e("ProductName", p.getProductName());
                                }
                                break;
                            case "ProductPrice":    //얘 어떻게 구현해야할지 모르겠음
                                if(p!=null) {
                                    String subTag = parser.getName();
                                    switch (subTag) {
                                        case "Price":
                                            p.setPrice(parser.nextText());
                                            Log.e("Price", p.getPrice());
                                            break;
                                        case "LowestPrice":
                                            p.setLowestPrice(parser.nextText());
                                            Log.e("LowestPrice", p.getLowestPrice());
                                            break;
                                    }
                                }
                                break;
                            case "ShipFee":
                                if(p!=null) {
                                    p.setShipFee(parser.nextText());
                                    Log.e("ShipFee", p.getShipFee());
                                }
                                break;
                            case "Point":
                                if(p!= null)
                                    p.setPoint(parser.nextText());
                                break;
                        }

                    }
                }
                eventType = parser.next();
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return p;
    }


    public int getCurrentSkip() {
        return currentSkip;
    }

    public void setProductCode(String productCode) { this.productCode = productCode; }

}
