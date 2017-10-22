package com.example.jean.webservice.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jean.webservice.Events.MyPartiesFragment;
import com.example.jean.webservice.Events.RegPartyFragment;
import com.example.jean.webservice.Helpers.CustomTypefaceSpan;
import com.example.jean.webservice.Login.AtvLogin;
import com.example.jean.webservice.R;

import net.danlew.android.joda.JodaTimeAndroid;

import static com.example.jean.webservice.Login.AtvLogin.LOGIN_PREFS;

public class AtvNavegacao extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        public FloatingActionButton fab;
        TextView headerTitle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(this);
        setContentView(R.layout.activity_atv_navegacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences settings = (SharedPreferences) getSharedPreferences(LOGIN_PREFS,0);


        // -------------------------------------------------- //
        // seta username e email no nav_header

        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigation.getHeaderView(0);
//        TextView nav_user = (TextView)hView.findViewById(R.id.tv_header_nav_username);
//        TextView nav_email = (TextView)hView.findViewById(R.id.tv_header_nav_email);
//        nav_user.setText(settings.getString("user_name", "nada"));
//        nav_email.setText(settings.getString("user_email", "nada"));

        // ----------------------- || ----------------------- //


        // -------------------------------------------------- //
        // Função do fab
        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send_white, getApplicationContext().getTheme()));
        } else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send_white,getApplicationContext().getTheme()));
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ----------------------- || ----------------------- //

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.atv_navegacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else{
            if (id == R.id.action_logout){
                SharedPreferences settings = getApplicationContext()
                        .getSharedPreferences(LOGIN_PREFS,
                        getApplicationContext().MODE_PRIVATE);
                settings.edit().clear().commit();

                Intent intent = new Intent(getApplication(), AtvLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager frag = getFragmentManager();

        if (id == R.id.nav_guestList) {
            frag.beginTransaction().replace(R.id.content_frame, new MyPartiesFragment()).commit();
        } else if (id == R.id.nav_regParty) {
            frag.beginTransaction().replace(R.id.content_frame, new RegPartyFragment()).commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
}
