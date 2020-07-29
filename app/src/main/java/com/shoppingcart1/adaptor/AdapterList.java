package com.shoppingcart1.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.shoppingcart1.R;
import com.shoppingcart1.helper.CustomVolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterList extends ArrayAdapter
{
    Context context;
    HashMap<String, ArrayList> hm;

    ArrayList altitle, alcategory,alsize,aldetails,alcolor,almaterialcaredetails,alorigionalprice,aldiscount,alprice,alexchangepolicydetails,alwarrentydetails,alimg;

    public AdapterList(Context context, int resource, HashMap<String, ArrayList> hm) {
        super(context, resource);

        this.context = context;
        this.hm=hm;

        getValues();
    }

    void getValues()
    {
        altitle=hm.get("keytitle");
//        alcategory=hm.get("keycategory");
//        alsize=hm.get("keysize");
//        aldetails=hm.get("keydetails");
//        alcolor=hm.get("keycolor");
//        almaterialcaredetails=hm.get("keymaterialcaredetails");
        alorigionalprice=hm.get("keyorigionalprice");
        aldiscount=hm.get("keydiscount");
        alprice=hm.get("keyprice");
//        alexchangepolicydetails=hm.get("keyexchangepolicydetails");
//        alwarrentydetails=hm.get("keywarrentydetails");
        alimg=hm.get("keyimg");
    }

    @Override
    public int getCount() {
        int size=altitle.size();
        return size;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.adapterlistview, null);

        TextView tv1 = (TextView) view.findViewById(R.id.gvv_title);
        TextView tv2 = (TextView) view.findViewById(R.id.gvv_price);
        TextView tv3 = (TextView) view.findViewById(R.id.gvv_original_price);
        TextView tv4 = (TextView) view.findViewById(R.id.gvv_discount);
        NetworkImageView gvv_offer_img = (NetworkImageView) view.findViewById(R.id.gvv_product_img);


        tv1.setText(altitle.get(position).toString());
        tv2.setText(alprice.get(position).toString());
        tv3.setText(alorigionalprice.get(position).toString());
        tv4.setText(aldiscount.get(position).toString());

        String url=alimg.get(position).toString();
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(gvv_offer_img, R.drawable.loadingg, android.R.drawable.ic_dialog_alert));
        gvv_offer_img.setImageUrl(url, imageLoader);

        //buttons click
//        ImageView moredetailbutton=(ImageView) view.findViewById(R.id.moredetailbutton);
//        moredetailbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context, moredetail.class);
//                intent.putExtra("title", altitle.get(position).toString());
//                intent.putExtra("origionalprice", alorigionalprice.get(position).toString());
//                intent.putExtra("discount", aldiscount.get(position).toString());
//                intent.putExtra("price", alprice.get(position).toString());
//                intent.putExtra("img", alimg.get(position).toString());
//
//                intent.putExtra("category", alcategory.get(position).toString());
//                intent.putExtra("size", alsize.get(position).toString());
//                intent.putExtra("color", alcolor.get(position).toString());
//                intent.putExtra("warrenty_details", alwarrentydetails.get(position).toString());
//                intent.putExtra("details", aldetails.get(position).toString());
//                intent.putExtra("materialcaredetails", almaterialcaredetails.get(position).toString());
//                intent.putExtra("exchangepolicydetails", alexchangepolicydetails.get(position).toString());
//                context.startActivity(intent);
//            }
//        });

        return view;
    }
    private ImageLoader imageLoader;
}

