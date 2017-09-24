package com.daffodilvarsity.diuinternationalaffairs;

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

import com.daffodilvarsity.diuinternationalaffairs.activity.AboutUsActivity;
import com.daffodilvarsity.diuinternationalaffairs.activity.DeveloperInfoActivity;
import com.daffodilvarsity.diuinternationalaffairs.activity.MOUlistActivity;
import com.daffodilvarsity.diuinternationalaffairs.activity.Profile;
import com.daffodilvarsity.diuinternationalaffairs.adapter.Event_Adapter;
import com.daffodilvarsity.diuinternationalaffairs.fragments.EventProgram;
import com.daffodilvarsity.diuinternationalaffairs.fragments.ExchangeProgram;
import com.daffodilvarsity.diuinternationalaffairs.fragments.ScholarshipProgram;

public class Home extends AppCompatActivity implements Toolbar.OnMenuItemClickListener,NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    Event_Adapter event_adapter;
    ViewPager viewPager;
    ViewPagerAdaptertwo viewPagerAdapter;
    TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        viewPager = (ViewPager)findViewById(R.id.ProgramsViewPager);

        tabLayout = (TabLayout) findViewById(R.id.ProgramsTabLayout);

        viewPagerAdapter = new ViewPagerAdaptertwo(getSupportFragmentManager());

        viewPagerAdapter.getFragments(new ExchangeProgram(),"Exchange Program");
        viewPagerAdapter.getFragments(new ScholarshipProgram(),"Scholarship Program");
        viewPagerAdapter.getFragments(new EventProgram(),"International Events");

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        if(item.getItemId()==R.id.Home){

        }
        if(item.getItemId()==R.id.Profile){

            Intent i = new Intent(this,Profile.class);
            startActivity(i);

        }
        else if(item.getItemId()==R.id.MouUniList){

            Intent i = new Intent(this,MOUlistActivity.class);
            startActivity(i);

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
    public boolean onMenuItemClick(MenuItem item) {


        return true;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Home.this.finishAffinity();
    }
}
