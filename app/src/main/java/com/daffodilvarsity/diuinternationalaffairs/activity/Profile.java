package com.daffodilvarsity.diuinternationalaffairs.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.daffodilvarsity.diuinternationalaffairs.Home;
import com.daffodilvarsity.diuinternationalaffairs.R;
import com.daffodilvarsity.diuinternationalaffairs.StudentDatabaseHelper;

public class Profile extends AppCompatActivity {

    Toolbar toolbar;
    TextView fullnametv,idtv,semestertv,emailtv,phonetv,departmenttv;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Profile.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fullnametv= (TextView) findViewById(R.id.userfullnameTv);
        idtv= (TextView) findViewById(R.id.useridTv);
        semestertv= (TextView) findViewById(R.id.usersemordesigTv);
        emailtv= (TextView) findViewById(R.id.usermailTv);
        phonetv= (TextView) findViewById(R.id.userphoneTv);
        departmenttv = (TextView)findViewById(R.id.userdepartmentTv);

        StudentDatabaseHelper studentDatabaseHelper = new StudentDatabaseHelper(Profile.this);
        Cursor result = studentDatabaseHelper.retriveData();
        result.moveToFirst();

        fullnametv.setText(result.getString(0));
        idtv.setText(result.getString(1));
        departmenttv.setText(result.getString(3));
        emailtv.setText(result.getString(5));
        phonetv.setText(result.getString(6));

        if(result.getString(7).equals("student")){

            if(result.getString(2).equals(1)){
                semestertv.setText(result.getString(2) +"st semester");
            }
            if(result.getString(2).equals(2)){
                semestertv.setText(result.getString(2) +"nd semester");
            }if(result.getString(2).equals(3)){
                semestertv.setText(result.getString(2) +"rd semester");
            }
            else{
                semestertv.setText(result.getString(2) +"th semester");
            }


        }
        else {
            semestertv.setText(result.getString(4));
        }




    }
}
