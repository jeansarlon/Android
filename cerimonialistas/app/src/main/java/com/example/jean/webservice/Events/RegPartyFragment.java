package com.example.jean.webservice.Events;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jean.webservice.Helpers.FormatDate;
import com.example.jean.webservice.Helpers.Http;
import com.example.jean.webservice.R;
import com.redmadrobot.inputmask.MaskedTextChangedListener;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.jean.webservice.Login.AtvLogin.LOGIN_PREFS;


public class RegPartyFragment extends Fragment {
    View myView;
    EditText et_partyName;
    EditText et_partyBirthPeople;
    EditText et_partydate;
    EditText et_partytime;
    TextView tv_regPartyTitle;
    String userId;
    FloatingActionButton fab;


    String partyName;
    String partyBirthPeople;
    String partyDate;
    String partyTime;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.reg_party_layout, container, false);

        et_partyName = (EditText) myView.findViewById(R.id.party_name);
        et_partyBirthPeople = (EditText) myView.findViewById(R.id.party_birth_people);
        et_partydate = (EditText) myView.findViewById(R.id.party_date);
        et_partytime = (EditText) myView.findViewById(R.id.party_time);
        tv_regPartyTitle = (TextView) myView.findViewById(R.id.tv_regPartyTitle);

        final MaskedTextChangedListener listener = new MaskedTextChangedListener(
                "[00]/[00]/[0000]",
                true,
                et_partydate,
                null,
                new MaskedTextChangedListener.ValueListener() {
                    @Override
                    public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue) {
                        Log.d(getClass().getSimpleName(), extractedValue);
                        Log.d(getClass().getSimpleName(), String.valueOf(maskFilled));
                    }
                }
        );

        et_partydate.addTextChangedListener(listener);
//        et_partydate.setOnFocusChangeListener(listener);
        et_partydate.setHint("Data");



        final MaskedTextChangedListener listenerTime = new MaskedTextChangedListener(
                "[00]:[00]",
                true,
                et_partytime,
                null,
                new MaskedTextChangedListener.ValueListener() {
                    @Override
                    public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue) {
                        Log.d(getClass().getSimpleName(), extractedValue);
                        Log.d(getClass().getSimpleName(), String.valueOf(maskFilled));
                    }
                }
        );

        et_partytime.addTextChangedListener(listenerTime);
//        et_partydate.setOnFocusChangeListener(listenerTime);
        et_partytime.setHint("Hora");

        Typeface tf = Typeface.createFromAsset(myView.getContext().getAssets(), "fonts/OpenSans-Regular.ttf");
        tv_regPartyTitle.setTypeface(tf);


        SharedPreferences settings = getActivity().getSharedPreferences(LOGIN_PREFS,0);
        userId = settings.getString("user_id", "nada");


        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partyName = et_partyName.getText().toString();
                partyBirthPeople = et_partyBirthPeople.getText().toString();
                partyDate = et_partydate.getText().toString();
                partyTime = et_partytime.getText().toString();

                if (
                       partyName.isEmpty()
                    || partyBirthPeople.isEmpty()
                    || partyDate.isEmpty()
                    || partyTime.isEmpty()
                ){
                    Snackbar.make(getView(),"Todos os campos são obrigatórios!!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
                }else {
                    FormatDate formatDate = new FormatDate();
                    partyDate = formatDate.toEUA(partyDate);
                    requisicaoVolleyC();
                }
            }
        });

        return myView;
    }


    private void requisicaoVolleyC(){
        // monta o objeto Json que será repassado ao WS
        JSONObject params = new JSONObject();
        try {
            params.put("id",userId);
            params.put("name", partyName);
            params.put("birthday_people", partyBirthPeople);
            params.put("date", partyDate);
            params.put("time", partyTime);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String url ="register_parties";

        Http http = new Http(getActivity().getApplicationContext());
        http.post(url, params, new Http.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                String status;

                try {
                    if (result.getBoolean("success") == true){
                        status = "Cadastrado com sucesso!";
                    } else {
                        status = "Erro ao cadastrar!";
                    }
                    reset();
                    Snackbar.make(getView(), status, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void reset(){
        et_partyName.setText("");
        et_partyBirthPeople.setText("");
        et_partydate.setText("");
        et_partytime.setText("");
    }

}
