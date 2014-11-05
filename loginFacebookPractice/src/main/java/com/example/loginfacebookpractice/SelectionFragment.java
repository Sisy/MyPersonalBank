package com.example.loginfacebookpractice;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.*;


/**
 * Created by zhangshiyue on 14-11-3.
 */
public class SelectionFragment extends Fragment {
    private static final String TAG = "SelectionFragment";

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.selection,
                container, false);
        return view;
    }
}
