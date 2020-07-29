package com.shoppingcart1.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shoppingcart1.R;
import com.shoppingcart1.helper.CustomVolleyRequest;

public class Moredetails extends AppCompatActivity {

    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moredetails);


        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        String origionalprice = bundle.getString("origionalprice");
        String discount = bundle.getString("discount");
        String price = bundle.getString("price");
//        String image = bundle.getString("imgbasestr");

//        String exchangepolicydetails = bundle.getString("exchangepolicydetails");
        String category = bundle.getString("category");
//        String color = bundle.getString("color");
//        String warrentydetails = bundle.getString("warrentydetails");
//        String date1 = bundle.getString("date1");
//        String time1 = bundle.getString("time1");


        TextView prodet_title = (TextView) findViewById(R.id.lapi_prodet_title);
        TextView prodet_price = (TextView) findViewById(R.id.lapi_prodet_price);
        TextView prodet_original_price = (TextView) findViewById(R.id.lapi_prodet_original_price);
        TextView prodet_discount = (TextView) findViewById(R.id.lapi_prodet_discount);
//
//        TextView prodet_product_details = (TextView) findViewById(R.id.lapi_prodet_details);
//          TextView prodet_color = (TextView) findViewById(R.id.lapi_prodet_color);
        TextView prodet_specifications = (TextView) findViewById(R.id.lapi_prodet_specifications);
//        TextView prodet_warrenty_details = (TextView) findViewById(R.id.lapi_prodet_warrenty_details);
//        TextView prodet_date1 = (TextView) findViewById(R.id.lapi_prodet_date1);
//        TextView prodet_time1 = (TextView) findViewById(R.id.lapi_prodet_time1);

       // NetworkImageView prodet_img = (NetworkImageView) findViewById(R.id.lapi_prodet_img);

        prodet_title.setText(title);
        prodet_price.setText(price);
        prodet_original_price.setText(origionalprice);
        prodet_discount.setText(discount);
//        prodet_product_details.setText(exchangepolicydetails);
        prodet_specifications.setText(category);
        //prodet_color.setText(color);
//        prodet_warrenty_details.setText(warrentydetails);
//        prodet_date1.setText(date1);
//        prodet_time1.setText(time1);

//        imageLoader = CustomVolleyRequest.getInstance(getApplicationContext()).getImageLoader();
//        imageLoader.get(image, ImageLoader.getImageListener(prodet_img, R.drawable.loadingg, android.R.drawable.ic_dialog_alert));
//        prodet_img.setImageUrl(image, imageLoader);



    }
}
