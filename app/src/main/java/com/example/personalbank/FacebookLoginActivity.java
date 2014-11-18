package com.example.personalbank;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

public class FacebookLoginActivity extends FragmentActivity {

    private UiLifecycleHelper uiHelper;
    private FacebookLoginFragement facebookLoginFragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

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

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Intent intent = new Intent(this, EventListFragmentActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Session session = Session.getActiveSession();
        if (session != null) {
            onSessionStateChange(session, session.getState(), null);
        }

        //uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }
}
