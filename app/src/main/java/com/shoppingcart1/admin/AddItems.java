package com.shoppingcart1.admin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shoppingcart1.R;
import com.shoppingcart1.adaptor.AdapterList;
import com.shoppingcart1.connection.IpConnect;
import com.shoppingcart1.customer.ProductDetails;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class AddItems extends AppCompatActivity {
    private static final int CAMERA_REQUESTONE = 1;
    String imgbasestr;
    ImageView img;
    EditText ettitle,et_product_details,etcolor,et_material_care_details,et_original_price,et_discount,et_price,et_exchange_policy_details,et_warrenty_details;
    Spinner spitem_category,spselect_size;
    Bitmap bitmap;
    Button btnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        btnn=findViewById(R.id.newbtn);
        ettitle=findViewById(R.id.admin_title);
        spitem_category=findViewById(R.id.admin_item_category);
        spselect_size=findViewById(R.id.admin_size);
        et_product_details=findViewById(R.id.admin_product_details);
        etcolor=findViewById(R.id.admin_color);
        et_material_care_details=findViewById(R.id.admin_material_care_details);
        et_original_price=findViewById(R.id.admin_original_price);
        et_discount=findViewById(R.id.admin_discount);
        et_price=findViewById(R.id.admin_price);
        et_exchange_policy_details=findViewById(R.id.admin_exchange_policy_details);
        et_warrenty_details=findViewById(R.id.admin_warrenty_details);




        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ProductDetails.class);
                startActivity(intent);
                finish();
            }
        });
        img=findViewById(R.id.admin_upload_image);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });


        AppCompatButton appCompatButton=(AppCompatButton) findViewById(R.id.admin_add_item_btn);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadBitmap(bitmap);
            }

        });}
    public void openCamera()
    {
        try
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Activity.RESULT_OK);
            }
            else
            {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(i, CAMERA_REQUESTONE);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e+"......", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        if (requestCode == CAMERA_REQUESTONE && resultCode == Activity.RESULT_OK)
        {
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                img.setImageBitmap(bitmap);

                imgbasestr=encodeImage(bitmap);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

    //---------------------------------image view and path----------------------------------------------
    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }
    private void uploadBitmap(final Bitmap bitmap) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        final String time1 = sdf1.format(calendar.getTime());
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        final String date1 = sdf2.format(calendar.getTime());

        final String title = ettitle.getText().toString();
        final String category = (String) spitem_category.getSelectedItem();
        final String size = (String) spselect_size.getSelectedItem();
        final String details = et_product_details.getText().toString();
        final String color = etcolor.getText().toString();
        final String materialcaredetails = et_material_care_details.getText().toString();
        final String origionalprice = et_original_price.getText().toString();
        final String discount = et_discount.getText().toString();
        final String price = et_price.getText().toString();
        final String exchangepolicydetails = et_exchange_policy_details.getText().toString();
        final String warrentydetails = et_warrenty_details.getText().toString();


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.43.117/shoppingcart/shoppingdemo.php/uploadImage", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error : " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap hm = new HashMap();
                hm.put("keytitle", title);
                hm.put("keycategory", category);
                hm.put("keysize", size);
                hm.put("keydetails", details);
                hm.put("keycolor", color);
                hm.put("keymaterialcaredetails", materialcaredetails);
                hm.put("keyorigionalprice", origionalprice);
                hm.put("keydiscount", discount);
                hm.put("keyprice", price);
                hm.put("keyexchangepolicydetails", exchangepolicydetails);
                hm.put("keywarrentydetails", warrentydetails);
                hm.put("keyimg", imgbasestr);
                hm.put("date1", date1);
                hm.put("time1", time1);
                return hm;
            }
        };
        requestQueue.add(stringRequest);
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }
//
//    public String getRealPathFromURI(Uri uri) {
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//        return cursor.getString(idx);
//    }



    }







