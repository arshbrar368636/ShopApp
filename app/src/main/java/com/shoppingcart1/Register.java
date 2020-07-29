package com.shoppingcart1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shoppingcart1.Validation.Validations;
import com.shoppingcart1.connection.IpConnect;
import com.shoppingcart1.session.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText etname, etemail, etpass;
    Spinner spcity;
    Button btn,loginbtn;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context=this;

        etname=findViewById(R.id.regidname);
        etemail=findViewById(R.id.regidemail);
        etpass=findViewById(R.id.regidpassword);
        spcity=findViewById(R.id.regidcity);
        btn=findViewById(R.id.regbtn);
        loginbtn=findViewById(R.id.reglogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog=new ProgressDialog(context);
                progressDialog.setMessage("Loading...Please Wait...!!!");
                progressDialog.show();

                final String name=etname.getText().toString();
                final String email=etemail.getText().toString();
                final String pass=etpass.getText().toString();
                final String city=(String)spcity.getSelectedItem();


                Validations rv=new Validations();
                if(!rv.name_validate(name))
                {
                    etname.setError("Invalid Name");
                    etname.setFocusable(true);
                }
                else if(!rv.email_validate(email))
                {
                    etemail.setError("Invalid Email");
                    etemail.setFocusable(true);
                }
                else if(!rv.pass_validate(pass))
                {
                    etpass.setError("Invalid password");
                    etpass.setFocusable(true);
                }
                else if(city.trim().equals("Select City"))
                {
                    Toast.makeText(getApplicationContext(), "Select Atleast one City", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, IpConnect.register, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                            SessionManager sm=new SessionManager(context);
                            sm.createSession("customer",name,email);

                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), "Error : "+error, Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hm=new HashMap<>();
                            hm.put("keyname", name);
                            hm.put("keyemail", email);
                            hm.put("keypass", pass);
                            hm.put("keycity", city);
                            return hm;
                        }
                    };

                    requestQueue.add(stringRequest);
                }

            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });

    }
}

