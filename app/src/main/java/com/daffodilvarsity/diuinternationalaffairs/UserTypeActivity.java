package com.daffodilvarsity.diuinternationalaffairs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserTypeActivity extends AppCompatActivity {



    private UserTypeActivity()
    {
        uniqInstance = new UserTypeActivity();
    }

    public static synchronized UserTypeActivity getInstance()
    {
        if (uniqInstance == null) {
            uniqInstance = new UserTypeActivity();
        }
        return uniqInstance;
    }
    private static UserTypeActivity uniqInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
    }
}
