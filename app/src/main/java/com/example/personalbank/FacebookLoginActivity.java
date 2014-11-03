package com.example.personalbank;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FacebookLoginActivity extends FragmentActivity {

    private FacebookLoginFragement facebookLoginFragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            facebookLoginFragement = new FacebookLoginFragement();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, facebookLoginFragement)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            facebookLoginFragement = (FacebookLoginFragement) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
    }
}
