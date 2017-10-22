package com.example.jean.cerimonialistas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import static com.example.jean.cerimonialistas.AtvLogin.LOGIN_PREFS;

public class AtvNavegacao extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        public FloatingActionButton fab;
        TextView headerTitle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_navegacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences settings = (SharedPreferences) getSharedPreferences(LOGIN_PREFS,0);


        // -------------------------------------------------- //
        // seta username e email no nav_header

        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigation.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.tv_header_nav_username);
        TextView nav_email = (TextView)hView.findViewById(R.id.tv_header_nav_email);
        nav_user.setText(settings.getString("user_name", "nada"));
        nav_email.setText(settings.getString("user_email", "nada"));

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

        if (id == R.id.nav_regFamily) {
            frag.beginTransaction().replace(R.id.content_frame, new FirstFragment()).commit();

        } else if (id == R.id.nav_regGuests) {
            frag.beginTransaction().replace(R.id.content_frame, new SecondFragment()).commit();
        } else if (id == R.id.nav_guestList) {
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
}
