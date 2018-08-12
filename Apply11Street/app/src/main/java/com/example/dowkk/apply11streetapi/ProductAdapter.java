package com.example.dowkk.apply11streetapi;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/*

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
*/


public class ProductAdapter extends ArrayAdapter<Product>{
    private Context context;
    private int resource;
    private List<Product> productList;

    private ImageLoader imageLoader;
    //private ImageLoaderConfiguration configuration;
    public ProductAdapter(Context context, int resource, List<Product> productList) {
        super(context, resource,productList);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.resource = resource;
        this.productList = productList;
        imageLoader = new ImageLoader(context);
        //imageLoader = ImageLoader.getInstance();
        //       this.configuration = new ImageLoaderConfiguration.Builder(this.context).build();
        //       imageLoader.init(ImageLoaderConfiguration.createDefault(this.context));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
//        return super.getView(position, convertView, parent);

        //getView메소드는 리스트뷰의 한줄의 아이템을 그리기 위한 뷰를 만들어내는 함수이고
        //한줄의 아이템에 대해서 UI를 인플레이션하고 그 객체를 리턴하면됨

        ProductViewHolder holder;
        if(convertView == null){
            //이미 인플레이션 한 뷰가 있다면 매개변수 convertView에 들어와 재사용 가능하므로
            //convertView가 null일때만 인플레이션 해줌
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.list_product_item, parent, false);
            holder = new ProductViewHolder();
            holder.imageView
                    = (ImageView) convertView.findViewById(R.id.product_img);
            holder.productName
                    = (TextView) convertView.findViewById(R.id.product_productName);
            holder.productPrice
                    = (TextView) convertView.findViewById(R.id.product_price);
            holder.productSeller
                    = (TextView) convertView.findViewById(R.id.product_seller);
            //처음 인플레이션 될 때 홀더 객체를 만들어서
            //홀더 셋트의 위젯 참조변수들이 findViewById

            //holder객체는 각 위젯들이 findViewById한 결과들 집합
            convertView.setTag(holder);


        }
        else{
            holder = (ProductViewHolder) convertView.getTag();

        }
        //getView 할 일
        //1. 껍데기 인플레이션하기(convertView가 null아니면 재사용 가능)
        //2. 껍데기 내부의 위젯들 객체 얻어도기 (findViewById)
        //3. 각 위젯에 데이터 바인딩하기


        Product p = productList.get(position);

        //여기부터 이제 홀더객체 안의 각  위젯에 book객체의 각 멤버면수값들이랑 바인딩하면 됨ㅇㅇ
        holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        holder.productName.setText(p.getProductName());
        holder.productPrice.setText("가격: " + p.getProductPrice());
        holder.productSeller.setText("판매자: " + p.getSeller());
        //book.getImage() < url에 접속해서 사진을 다운받아 디코딩해서 ImageView에 set
        new ImageDownLoader(holder.imageView).execute(p.getProductImage());
        imageLoader.DisplayImage(p.getProductImage(), holder.imageView);
        return convertView;

    }

    class ImageDownLoader extends AsyncTask<String, Void, Bitmap>
    {
        ImageView imageView;
        public ImageDownLoader(ImageView imageView){
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                BufferedInputStream bi = new BufferedInputStream(url.openStream());
                bitmap = BitmapFactory.decodeStream(bi);
                bi.close();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if(result != null)
                imageView.setImageBitmap(result);
        }

    }


    static class ProductViewHolder{
        public ImageView imageView;
        public TextView productName;
        public TextView productPrice;
        public TextView productSeller;
    }

}
