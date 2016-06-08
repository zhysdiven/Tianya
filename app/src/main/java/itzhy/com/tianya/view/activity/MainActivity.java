package itzhy.com.tianya.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import itzhy.com.tianya.R;
import itzhy.com.tianya.adapter.PageAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("天涯");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabs = (TabLayout) findViewById(R.id.main_tabs);
        ViewPager vpager = (ViewPager) findViewById(R.id.main_vpager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.addTab(tabs.newTab().setText("媒体"));
        tabs.addTab(tabs.newTab().setText("干货"));
        tabs.addTab(tabs.newTab().setText("娱乐"));
        tabs.addTab(tabs.newTab().setText("应用"));

        ArrayList<String> title = new ArrayList<>();
        title.add("main");
        title.add("ghuo");
        title.add("play");
        title.add("app");
        pageAdapter = new PageAdapter(getSupportFragmentManager(), title);
        vpager.setAdapter(pageAdapter);
        tabs.setupWithViewPager(vpager);

       // initWeather();
    }

    /**
     * TODO init weather
     */
    private void initWeather() {
        SimpleDraweeView header = (SimpleDraweeView) findViewById(R.id.img_header);
        SimpleDraweeView weath = (SimpleDraweeView) findViewById(R.id.img_weather);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this, VlcVideoActivity.class));
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_manage) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_share) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_send) {
            drawer.openDrawer(GravityCompat.START);
        }
        return true;
    }
}
