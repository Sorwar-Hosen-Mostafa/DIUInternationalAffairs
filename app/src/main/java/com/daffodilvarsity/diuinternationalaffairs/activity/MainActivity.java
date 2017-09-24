package com.daffodilvarsity.diuinternationalaffairs.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daffodilvarsity.diuinternationalaffairs.ActivityTeacherRegistration;
import com.daffodilvarsity.diuinternationalaffairs.Home;
import com.daffodilvarsity.diuinternationalaffairs.intro_manager.IntroManager;
import com.daffodilvarsity.diuinternationalaffairs.R;
import com.daffodilvarsity.diuinternationalaffairs.StudentDatabaseHelper;
import com.daffodilvarsity.diuinternationalaffairs.StudentRegistration;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private IntroManager introManager;
    private int[] layouts;
    private TextView[] dots;
    private LinearLayout dotslayout;
    private Button next;
    private ViewPagerAdapter viewPagerAdapter;


    private EditText fullname_edittext, id_edittext, diuemail_edittext, phone_edittext;
    private Spinner semester;
    private Button test;

    LinearLayout studentlayout,teacherlayout;

    StudentRegistration studentRegistration;

    String student_full_name;
    String student_id;
    String student_semester;
    String student_diu_email;
    String student_phone;

    String user_type;





    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        introManager = new IntroManager(this);

        student_semester=null;
        student_full_name = null;



        if (!introManager.check()) {

            Intent i = new Intent(this, Home.class);
            startActivity(i);
            this.finish();
        } else {

            setContentView(R.layout.activity_main);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            dotslayout = (LinearLayout) findViewById(R.id.dotholder);
            next = (Button) findViewById(R.id.button_next);
            layouts = new int[]{R.layout.activity_screen_1, R.layout.activity_screen_2, R.layout.activity_screen_3, R.layout.activity_screen_4, R.layout.activity_user_type};

            addBottomDot(0);
            ChangeStatusBarColor();
            viewPagerAdapter = new ViewPagerAdapter();
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.addOnPageChangeListener(viewListener);



            test = (Button) findViewById(R.id.testbtn);





            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int current = getItem(+1);
                    if (current < layouts.length) {
                        viewPager.setCurrentItem(current);



                    } else {

                        if(user_type==null){
                            Toast.makeText(MainActivity.this, "please select one option and then proceed" , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(user_type=="student"){

                                startActivity(new Intent(MainActivity.this,StudentRegistration.class).putExtra("type",user_type));

                            }else {
                                startActivity(new Intent(MainActivity.this,ActivityTeacherRegistration.class).putExtra("type",user_type));
                            }
                        }

                        studentlayout = (LinearLayout) findViewById(R.id.studentlayout);
                        teacherlayout = (LinearLayout) findViewById(R.id.teacherlayout);

                        studentlayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                studentlayout.setBackgroundColor(0x44FFFFFF);
                                teacherlayout.setBackgroundColor(Color.TRANSPARENT);
                                user_type = "student";
                            }
                        });
                        teacherlayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                teacherlayout.setBackgroundColor(0x44FFFFFF);
                                studentlayout.setBackgroundColor(Color.TRANSPARENT);
                                user_type = "teacher";
                            }
                        });



                    }
                }
            });


        }


    }

    private int getItem(int i) {


        return viewPager.getCurrentItem() + 1;
    }

    private void addBottomDot(int position) {

        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInActive = getResources().getIntArray(R.array.dot_inactive);
        dotslayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInActive[position]);
            dotslayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(colorActive[position]);
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addBottomDot(position);
            if (position == layouts.length - 1) {
                next.setText("Proceed");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private void ChangeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    public class ViewPagerAdapter extends PagerAdapter {


        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View v = layoutInflater.inflate(layouts[position], container, false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }
    }

}
