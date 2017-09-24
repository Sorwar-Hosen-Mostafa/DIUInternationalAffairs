package com.daffodilvarsity.diuinternationalaffairs.intro_manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jibunnisa on 3/11/2017.
 */

public class IntroManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public IntroManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("first",0);
        editor = sharedPreferences.edit();

    }

    public void setfirst(boolean isfirst){
        editor.putBoolean("check",isfirst);
        editor.commit();

    }
    public boolean check(){
        return sharedPreferences.getBoolean("check",true);
    }
}
