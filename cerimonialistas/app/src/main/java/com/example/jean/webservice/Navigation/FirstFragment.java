package com.example.jean.webservice.Navigation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jean.webservice.R;

import static com.example.jean.webservice.R.id.fab;

/**
 * Created by jeansarlon on 26/09/16.
 */

public class FirstFragment extends Fragment {
    EditText tv;
    View myView;
    FloatingActionButton fccc;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);
        fccc = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        tv = (EditText) myView.findViewById(R.id.NumberOfFamily);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "buceta", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "First", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return myView;
    }
}
