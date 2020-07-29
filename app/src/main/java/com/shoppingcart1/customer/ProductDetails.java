package com.shoppingcart1.customer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shoppingcart1.MainActivity;
import com.shoppingcart1.R;
import com.shoppingcart1.adaptor.AdapterList;
import com.shoppingcart1.admin.AdminProfile;
import com.shoppingcart1.connection.IpConnect;
import com.shoppingcart1.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDetails extends AppCompatActivity {
    JSONObject jsonRootObject;
    JSONArray jsonArray;

    ArrayList altitle, alcategory,alsize,aldetails,alcolor,almaterialcaredetails,alorigionalprice,aldiscount,alprice,alexchangepolicydetails,alwarrentydetails,alimg,aldate1,altime1;

    Context context;

    android.widget.ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        context=this;

        lv=findViewById(R.id.emplistview);

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, IpConnect.viewList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response,Toast.LENGTH_SHORT).show();


                altitle=new ArrayList();
                aldiscount=new ArrayList();
                alprice=new ArrayList();
                alorigionalprice=new ArrayList();
                alimg=new ArrayList();
//                aldate1=new ArrayList();
//                altime1=new ArrayList();


                String title=null,discount=null,price=null,origionalprice=null,imgbasestr=null,date1=null,time1=null;
                try
                {
                    jsonRootObject = new JSONObject(response);
                    jsonArray = jsonRootObject.optJSONArray("server_response");
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        title = jsonObject.optString("title").toString();
                        discount = jsonObject.optString("discount").toString();
                        price = jsonObject.optString("price").toString();
                        origionalprice = jsonObject.optString("origionalprice").toString();
                        imgbasestr = jsonObject.optString("image").toString();
//                        date1 = jsonObject.optString("date1").toString();
//                        time1 = jsonObject.optString("time1").toString();



                        altitle.add(title);
                        aldiscount.add(discount);
                        alprice.add(price);
                        alorigionalprice.add(origionalprice);
                        alimg.add(imgbasestr);
//                        aldate1.add(date1);
//                        altime1.add(time1);

                    }

                    HashMap<String, ArrayList> hm=new HashMap<>();
                    hm.put("keytitle",altitle);
                    hm.put("keydiscount",aldiscount);
                    hm.put("keyprice",alprice);
                    hm.put("keyorigionalprice",alorigionalprice);
                    hm.put("keyimg",alimg);
//                    hm.put("date1",aldate1);
//                    hm.put("time1",altime1);


                    View view =getWindow().getDecorView().getRootView();
                    AdapterList at=new AdapterList(context, view.getId(),hm);
                    lv.setAdapter(at);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Intent intent=new Intent(getApplicationContext(),Moredetails.class);
                            intent.putExtra("title", altitle.get(position).toString());
                            intent.putExtra("origionalprice", alorigionalprice.get(position).toString());
                            intent.putExtra("discount", aldiscount.get(position).toString());
                            intent.putExtra("price", alprice.get(position).toString());
//                            intent.putExtra("image", alimg.get(position).toString());
//
//                            intent.putExtra("size", alsize.get(position).toString());
                            intent.putExtra("category", alcategory.get(position).toString());
//                            intent.putExtra("color", alcolor.get(position).toString());
//                              intent.putExtra("warrentydetails", alwarrentydetails.get(position).toString());
//                            intent.putExtra("date1", aldate1.get(position).toString());
//                            intent.putExtra("time1", altime1.get(position).toString());

                            startActivity(intent);
                            finish();
                        }

                    });
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error : "+error,Toast.LENGTH_SHORT).show();

                }

        });
        requestQueue.add(stringRequest);


    }
}
