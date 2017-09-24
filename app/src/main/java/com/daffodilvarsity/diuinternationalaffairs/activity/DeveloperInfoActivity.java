package com.daffodilvarsity.diuinternationalaffairs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.daffodilvarsity.diuinternationalaffairs.Home;
import com.daffodilvarsity.diuinternationalaffairs.R;

public class DeveloperInfoActivity extends AppCompatActivity {


    Toolbar toolbar;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(DeveloperInfoActivity.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_info);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
