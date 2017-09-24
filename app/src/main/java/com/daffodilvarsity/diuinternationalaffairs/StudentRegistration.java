package com.daffodilvarsity.diuinternationalaffairs;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.daffodilvarsity.diuinternationalaffairs.activity.MainActivity;
import com.daffodilvarsity.diuinternationalaffairs.intro_manager.IntroManager;

public class StudentRegistration extends AppCompatActivity {

//    private StudentRegistration()
//    {
//        uniqInstance = new StudentRegistration();
//    }
//
//    public static synchronized StudentRegistration getInstance()
//    {
//        if (uniqInstance == null) {
//            uniqInstance = new StudentRegistration();
//        }
//        return uniqInstance;
//    }
    //  private static StudentRegistration uniqInstance;


    Spinner semester, department;
    int student_semester = 0;
    String student_department, student_full_name, student_id, student_diu_email, student_phone, user_type;
    EditText fullname_edittext, id_edittext, diuemail_edittext, phone_edittext;


    private IntroManager introManager;
    boolean EmailIsValid;
    final String emailPattern = "[a-zA-Z0-9._-]+@diu.edu.bd";
    final String IdPattern = "[0-9]{3}+-+[0-9]{2}+-+[0-9]{3,4}";
    final String PhonePattern = "01+[5-9]+[0-9]{8}";
    boolean IdValid;
    boolean PhoneValid;
    private StudentDatabaseHelper studentDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        introManager = new IntroManager(this);

        if (!introManager.check()) {

            Intent i = new Intent(this, Home.class);
            startActivity(i);
            this.finish();
        }
        else {
            setContentView(R.layout.activity_student_registration);

            semester = (Spinner) findViewById(R.id.student_semester_spinner);
            department = (Spinner) findViewById(R.id.student_department_spinner);

            fullname_edittext = (EditText) findViewById(R.id.student_fullname_edittext);
            id_edittext = (EditText) findViewById(R.id.student_id_edittext);
            diuemail_edittext = (EditText) findViewById(R.id.student_diuemail_edittext);
            phone_edittext = (EditText) findViewById(R.id.student_phone_edittext);
        }



    }

    public void Register(View view) {

        user_type = getIntent().getStringExtra("type");
        Toast.makeText(StudentRegistration.this, " "+semester.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        switch (semester.getSelectedItem().toString()){
            case "1st":
                student_semester = 1;
                break;
            case "2nd":
                student_semester = 2;
                break;
            case "3rd":
                student_semester = 3;
                break;
            case "4th":
                student_semester = 4;
                break;
            case "5th":
                student_semester = 5;
                break;
            case "6th":
                student_semester = 6;
                break;
            case "7th":
                student_semester = 7;
                break;
            case "8th":
                student_semester = 8;
                break;
            case "9th":
                student_semester = 9;
                break;
            case "10th":
                student_semester = 10;
                break;
            case "11th":
                student_semester = 11;
                break;
            case "12th":
                student_semester = 12;
                break;
        }

        student_department = department.getSelectedItem().toString();
        student_full_name = fullname_edittext.getText().toString();

        if (diuemail_edittext.getText().toString().trim().matches(emailPattern)) {
            EmailIsValid = true;
        } else {
            EmailIsValid = false;
        }

        if (id_edittext.getText().toString().trim().matches(IdPattern)) {
            IdValid = true;
        } else {
            IdValid = false;
        }
        if (phone_edittext.getText().toString().trim().matches(PhonePattern)) {
            PhoneValid = true;
        } else {
            PhoneValid = false;
        }

        if (student_full_name.length() < 1) {
            fullname_edittext.setError("Provide your full name");
        }
        if (IdValid) {
            student_id = id_edittext.getText().toString();
        } else {
            id_edittext.setError("Provide your valid student id");
        }
        if (student_semester == 0) {
            Toast.makeText(StudentRegistration.this, "Select your semester", Toast.LENGTH_SHORT).show();
        }
        if (EmailIsValid) {
            student_diu_email = diuemail_edittext.getText().toString();
        } else {
            diuemail_edittext.setError("Provide your valid diu email");
        }
        if (PhoneValid) {
            student_phone = phone_edittext.getText().toString();
        } else {

            phone_edittext.setError("Provide a valid phone number");

        }


        if (student_full_name != null && student_semester != 0 && IdValid && PhoneValid && EmailIsValid) {

            studentDatabaseHelper = new StudentDatabaseHelper(StudentRegistration.this);
            sqLiteDatabase = studentDatabaseHelper.getWritableDatabase();

            boolean check = studentDatabaseHelper.InsertStudentInfo(student_full_name,student_id,student_semester,student_department,student_diu_email,student_phone,user_type,sqLiteDatabase);

            if (check) {
                introManager.setfirst(false);
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(StudentRegistration.this)
                        .setTitle("Success!!")
                        .setMessage("thank you for providing your informations. To continue press ok")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(StudentRegistration.this, Home.class);
                                startActivity(i);
                                finish();
                            }
                        });
                alertDialog2.show();
            } else {
                AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(StudentRegistration.this)
                        .setTitle("Somthing went wrong!!")
                        .setMessage("Please review your informations or contact with authority.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                alertDialog3.show();
            }


        }
    }
}
