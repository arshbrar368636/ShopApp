package com.shoppingcart1.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shoppingcart1.R;

public class AdminProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
    }
    public void additems(View view)
    {
        Intent intent=new Intent(getApplicationContext(), AddItems.class);
        startActivity(intent);
    }
}
