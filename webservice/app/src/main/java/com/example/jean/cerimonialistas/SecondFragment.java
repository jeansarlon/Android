package com.example.jean.cerimonialistas;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jeansarlon on 26/09/16.
 */

public class SecondFragment extends Fragment {
    View myView;
    FloatingActionButton fccc;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.second_layout, container, false);
        fccc = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Second", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return myView;
    }
}
