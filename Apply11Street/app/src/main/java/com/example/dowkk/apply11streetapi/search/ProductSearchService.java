package com.example.dowkk.apply11streetapi.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.net.URL;
import java.util.Locale;

import okhttp3.HttpUrl;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class ProductSearchService {
    // 요청할 URL
    private static final String URL = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=d74ff69646da36ffd552514f1d7de3a6&apiCode=ProductSearch&keyword=";

    //한번의 요청에서 받아올 아이템 갯수
    private static final int DISPLAY_ITEM_COUNT=10;

    // 검색할 키워드
    private String keyword;

    private Context context;

    public ProductSearchService() {}

    public ProductSearchService(String keyword){
        this.keyword = keyword;
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
                                if (p != null) {
                                    p.setProductImage(parser.nextText());
                                    new ImageDownload().execute(p.getProductImage());
                                }
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
                                if (p != null) {
                                    p.setProductDetailUrl(parser.nextText());
                                    Log.e("detail Url", p.getProductDetailUrl());
                                }
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

    public void setKeyword(String keyword) { this.keyword = keyword; }

    public void setContext(Context context) {
        this.context = context;
    }

    class ImageDownload extends AsyncTask<String, Void, Void> {

        private String fileName;
        private final String SAVE_FOLDER = "/save_folder";

        @Override

        protected Void doInBackground(String... params) {

            //다운로드 경로를 지정
            String savePath = Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER;
            File dir = new File(savePath);

            //상위 디렉토리가 존재하지 않을 경우 생성
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //파일 이름 :날짜_시간
            Date day = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA);
            fileName = String.valueOf(sdf.format(day));

            //웹 서버 쪽 파일이 있는 경로
            String fileUrl = params[0];

            //다운로드 폴더에 동일한 파일명이 존재하는지 확인
            if (new File(savePath + "/" + fileName).exists() == false) {
            } else {
            }

            String localPath = savePath + "/" + fileName + ".jpg";

            try {

                URL imgUrl = new URL(fileUrl);

                //서버와 접속하는 클라이언트 객체 생성
                HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
                int len = conn.getContentLength();
                byte[] tmpByte = new byte[len];

                //입력 스트림을 구한다
                InputStream is = conn.getInputStream();
                File file = new File(localPath);

                //파일 저장 스트림 생성
                FileOutputStream fos = new FileOutputStream(file);
                int read;

                //입력 스트림을 파일로 저장
                for (;;) {
                    read = is.read(tmpByte);
                    if (read <= 0) {
                        break;
                    }
                    fos.write(tmpByte, 0, read); //file 생성
                }
                is.close();
                fos.close();
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //저장한 이미지 열기
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String targetDir = Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER;
            File file = new File(targetDir + "/" + fileName + ".jpg");

            //type 지정 (이미지)
            i.setDataAndType(Uri.fromFile(file), "image/*");
            context.startActivity(i);

            //이미지 스캔해서 갤러리 업데이트
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        }
    }

}
