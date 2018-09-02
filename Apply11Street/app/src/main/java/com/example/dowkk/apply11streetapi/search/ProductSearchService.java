package com.example.dowkk.apply11streetapi.search;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class ProductSearchService {
    // 요청할 URL
    private static final String URL = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=d74ff69646da36ffd552514f1d7de3a6&apiCode=ProductSearch&keyword=";

    //한번의 요청에서 받아올 아이템 갯수
    private static final int DISPLAY_ITEM_COUNT=10;

    //현재 페이지를 알기 위한 상태값
    private int currentSkip=1;

    // 검색할 키워드
    private String keyword;

    public ProductSearchService() {}

    public ProductSearchService(String keyword){
        this.keyword = keyword;
    }

    public void nextPage() {
        currentSkip += DISPLAY_ITEM_COUNT;
    }

    public List<Product> search() {
        List<Product> list = null;
        try {
            URL url;
            url = new URL(URL  + URLEncoder.encode(keyword, "EUC-KR"));

            URLConnection urlConn = url.openConnection();

            // xml파서객체만들고
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            // 요청에 대한 응답 결과를 파서에 세팅
            parser.setInput(new InputStreamReader(urlConn.getInputStream(),"EUC-KR"));

            int eventType = parser.getEventType();
            Product p = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.END_DOCUMENT: // 문서의 끝
                        break;
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<Product>();
                        break;
                    case XmlPullParser.END_TAG: {
                        String tag = parser.getName();
                        if (tag.equals("Product")) {
                            list.add(p);
                            p = null;
                        }
                    }
                    case XmlPullParser.START_TAG: {
                        String tag = parser.getName();
                        switch (tag) {
                            case "Product":
                                p = new Product();
                                break;
                            case "ProductCode":
                                if (p != null)
                                    p.setProductCode(parser.nextText());
                                break;
                            case "ProductName":
                                if (p != null)
                                    p.setProductName(parser.nextText());
                                break;
                            case "ProductImage":
                                if (p != null)
                                    p.setProductImage(parser.nextText());
                                break;
                            case "ProductPrice":
                                if (p != null)
                                    p.setProductPrice(parser.nextText());
                                break;
                            case "Seller":
                                if (p != null)
                                    p.setSeller(parser.nextText());
                                break;
                            case "Rating":
                                if (p != null)
                                    p.setRating(parser.nextText());
                                break;
                            case "DetailPageUrl":
                                if (p != null)
                                    p.setProductDetailUrl(parser.nextText());
                                break;
                            case "SalePrice":
                                if (p != null)
                                    p.setSalePrice(parser.nextText());
                                break;
                            case "Delivery":
                                if (p != null)
                                    p.setDelivery(parser.nextText());
                                break;
                            case "ReviewCount":
                                if (p != null)
                                    p.setReviewCount(parser.nextText());
                                break;
                            case "BuySatisfy":
                                if (p != null)
                                    p.setBuySatisfy(parser.nextText());
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
        return list;
    }

    public int getCurrentSkip() {
        return currentSkip;
    }

    public void setKeyword(String keyword) { this.keyword = keyword; }
}
