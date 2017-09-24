package com.daffodilvarsity.diuinternationalaffairs.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.daffodilvarsity.diuinternationalaffairs.Home;
import com.daffodilvarsity.diuinternationalaffairs.R;
import com.daffodilvarsity.diuinternationalaffairs.ViewPagerAdaptertwo;
import com.daffodilvarsity.diuinternationalaffairs.adapter.Event_Adapter;
import com.daffodilvarsity.diuinternationalaffairs.adapter.MouItemAdapter;
import com.daffodilvarsity.diuinternationalaffairs.fragments.EventProgram;
import com.daffodilvarsity.diuinternationalaffairs.fragments.ExchangeProgram;
import com.daffodilvarsity.diuinternationalaffairs.fragments.Organization;
import com.daffodilvarsity.diuinternationalaffairs.fragments.ScholarshipProgram;
import com.daffodilvarsity.diuinternationalaffairs.fragments.University;

public class MOUlistActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener{


    Toolbar toolbar;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    MouItemAdapter mouItemAdapter;
    ViewPager viewPager;
    ViewPagerAdaptertwo viewPagerAdapter;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moulist);


        viewPager = (ViewPager)findViewById(R.id.ProgramsViewPager);

        tabLayout = (TabLayout) findViewById(R.id.ProgramsTabLayout);

        viewPagerAdapter = new ViewPagerAdaptertwo(getSupportFragmentManager());

        viewPagerAdapter.getFragments(new University(),"International Universities");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DIU");
        getSupportActionBar().setSubtitle("International Affairs");


        toolbar.setOnMenuItemClickListener(this);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);



        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {


        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.Home){

            Intent i = new Intent(this,Home.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.Profile){
            Intent i = new Intent(this,Profile.class);
            startActivity(i);

        }
        else if(item.getItemId()==R.id.MouUniList){

        }
        else if(item.getItemId()==R.id.AboutUs){
            Intent i = new Intent(this,AboutUsActivity.class);
            startActivity(i);

        }
        else if(item.getItemId()==R.id.DeveloperInfo){
            Intent i = new Intent(this,DeveloperInfoActivity.class);
            startActivity(i);

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MOUlistActivity.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

}
