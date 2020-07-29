package com.shoppingcart1.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.shoppingcart1.login;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sp;
    private static final String PREF_FILE_NAME = "shopping_cart_session_details";
    int PRIVATE_MODE = 0;
    SharedPreferences.Editor editor;


    Context context;
    boolean checkLogin=false;



    private static final String IS_LOGIN = "key_IsLoggedIn";
    public static final String KEY_USER_TYPE = "key_session_usertype";
    public static final String KEY_NAME = "key_session_name";
    public static final String KEY_EMAIL = "key_session_email";

    // Constructor
    public SessionManager(Context context)
    {
        this.context = context;
        sp = context.getSharedPreferences(PREF_FILE_NAME, PRIVATE_MODE);
        editor = sp.edit();
    }

    //create login session in case of register (when user first time register) and in login time
    public void createSession(String user_type, String name, String email)
    {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USER_TYPE, user_type);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public void setValuesInSession(String key, String value)
    {
        editor.putString(key, value);
        editor.commit();
    }

    public String getValuesFromSession(String key)
    {
        return sp.getString(key, null);
    }

    public void updateSessionValues(String key, String value)
    {
        editor.putString(key, value);
        editor.apply();
    }

    public boolean checkSession()
    {
        if(sp.contains(KEY_EMAIL))
        {
            checkLogin=true;
        }
        else
        {
            checkLogin=false;
        }
        return checkLogin;
    }

    public HashMap<String, String> getSession()
    {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put(KEY_USER_TYPE, sp.getString(KEY_USER_TYPE, null));
        hm.put(KEY_NAME, sp.getString(KEY_NAME, null));
        hm.put(KEY_EMAIL, sp.getString(KEY_EMAIL, null));
        return hm;
    }

    public void logoutSession()
    {
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        context.startActivity(i);
    }
}

