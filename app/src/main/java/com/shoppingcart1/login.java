package com.shoppingcart1;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shoppingcart1.admin.AdminProfile;
import com.shoppingcart1.connection.IpConnect;
import com.shoppingcart1.session.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    EditText etemail, etpass;
    Button btn;
    Context context;
    JSONObject jsonRootObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=this;

        etemail=findViewById(R.id.edittext1);
        etpass=findViewById(R.id.edittext2);
        btn=findViewById(R.id.open_login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Loading....Please Wait....!!!");
                progressDialog.show();

                final String email1=etemail.getText().toString();
                final String pass1=etpass.getText().toString();

                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, IpConnect.login, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        if(response.trim().equals("fails"))
                        {
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                        String name=null, email=null,module=null;
                        try

                        {
                            jsonRootObject = new JSONObject(response);
                            jsonArray = jsonRootObject.optJSONArray("server_response");
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                name = jsonObject.optString("name").toString();
                                email = jsonObject.optString("email").toString();
                                module = jsonObject.optString("module").toString();
                            }
                            if(module.equals("admin"))
                            {
                                Intent i=new Intent(getApplicationContext(), AdminProfile.class);
                                startActivity(i);
                                finish();
                            }
                            else if(module.equals("customer"))
                            {
                                SessionManager sm=new SessionManager(getApplicationContext());
                                sm.createSession(module, name, email);

                                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }}
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), "Error : "+error, Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap hm=new HashMap();
                        hm.put("keyemail", email1);
                        hm.put("keypass", pass1);
                        return hm;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    public void openregister(View view)
    {
        Intent i=new Intent(getApplicationContext(), Register.class);
        startActivity(i);
    }
}
