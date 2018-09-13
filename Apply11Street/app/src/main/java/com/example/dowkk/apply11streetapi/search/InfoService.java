package com.example.dowkk.apply11streetapi.search;

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

public class InfoService {
    // 요청할 URL
    private static final String URL = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=d74ff69646da36ffd552514f1d7de3a6&apiCode=ProductInfo&productCode=";
    private String productCode;

    public InfoService() {}
    public InfoService(String productCode) {    this.productCode = productCode;  }

    public Product getInfo() {
        Product p = null;
        try {
            java.net.URL url;
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
                                }
                                break;
                            case "ProductName":
                                if(p!=null) {
                                    p.setProductName(parser.nextText());
                                }
                                break;
                            case "ProductPrice":    //얘 어떻게 구현해야할지 모르겠음
                                if(p!=null) {
                                    String subTag = parser.getName();
                                    switch (subTag) {
                                        case "Price":
                                            p.setPrice(parser.nextText());
                                            break;
                                        case "LowestPrice":
                                            p.setLowestPrice(parser.nextText());
                                            break;
                                    }
                                }
                                break;
                            case "ShipFee":
                                if(p!=null) {
                                    p.setShipFee(parser.nextText());
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

    public void setProductCode(String productCode) { this.productCode = productCode; }

}
