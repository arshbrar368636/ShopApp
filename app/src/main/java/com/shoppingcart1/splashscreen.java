package com.shoppingcart1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.shoppingcart1.admin.AdminProfile;
import com.shoppingcart1.session.SessionManager;

import java.util.HashMap;

public class splashscreen extends AppCompatActivity {
    Thread t = new Thread() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);

                SessionManager sm = new SessionManager(getApplicationContext());
                boolean b = sm.checkSession();
                if (b) {
                    HashMap hm=sm.getSession();
                    String module=hm.get("key_session_usertype").toString();
                    if(module.equals("admin"))
                    {
                        Intent i=new Intent(getApplicationContext(), AdminProfile.class);
                        startActivity(i);
                        finish();
                    }
                    else if(module.equals("customer"))
                    {
                        Intent i=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Intent intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                System.out.print(e);
            }
        }
    };
    ImageView fadeimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        fadeimage=findViewById(R.id.fade_image);
        Animation anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        fadeimage.startAnimation(anim);
        t.start();
    }
}
