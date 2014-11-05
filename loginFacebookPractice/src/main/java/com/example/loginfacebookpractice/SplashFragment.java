package com.example.loginfacebookpractice;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.*;
import java.util.*;

import com.facebook.widget.LoginButton;

/**
 * Created by zhangshiyue on 14-11-3.
 */
public class SplashFragment extends Fragment {

    private static final String TAG = "SplashFragment";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash,
                container, false);
        return view;
    }
}
