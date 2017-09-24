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

import com.daffodilvarsity.diuinternationalaffairs.intro_manager.IntroManager;

public class ActivityTeacherRegistration extends AppCompatActivity {

    Spinner designation, department;
    String teacher_designation,teacher_department, teacher_full_name, student_id, student_diu_email, student_phone, user_type;
    EditText fullname_edittext, id_edittext, diuemail_edittext, phone_edittext;


    private IntroManager introManager;
    boolean EmailIsValid;
    final String emailPattern = "[a-zA-Z0-9._-]+@diu.edu.bd";
    final String IdPattern = "[0-9]{7,20}";
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

            setContentView(R.layout.activity_teacher_registration);
            designation = (Spinner) findViewById(R.id.teacher_designation_spinner);
            department = (Spinner) findViewById(R.id.teacher_department_spinner);

            fullname_edittext = (EditText) findViewById(R.id.teacher_fullname_edittext);
            id_edittext = (EditText) findViewById(R.id.teacher_id_edittext);
            diuemail_edittext = (EditText) findViewById(R.id.teacher_diuemail_edittext);
            phone_edittext = (EditText) findViewById(R.id.teacher_phone_edittext);
        }



    }

    public void Register(View view) {

        user_type = getIntent().getStringExtra("type");
        teacher_designation = designation.getSelectedItem().toString();
        teacher_department = department.getSelectedItem().toString();
        teacher_full_name = fullname_edittext.getText().toString();

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

        if (teacher_full_name.length() < 1) {
            fullname_edittext.setError("Provide your full name");
        }
        if (IdValid) {
            student_id = id_edittext.getText().toString();
        } else {
            id_edittext.setError("Provide your valid employee id");
        }
        if (teacher_designation==null) {
            Toast.makeText(ActivityTeacherRegistration.this, "Select your designation.", Toast.LENGTH_SHORT).show();
        }

        if (teacher_department==null) {
            Toast.makeText(ActivityTeacherRegistration.this, "Select your department.", Toast.LENGTH_SHORT).show();
        }
        if (EmailIsValid) {
            student_diu_email = diuemail_edittext.getText().toString();
        } else {
            Toast.makeText(ActivityTeacherRegistration.this, "Provide your valid diu email please.", Toast.LENGTH_SHORT).show();
            //student_diu_email = "error-error-error";
            diuemail_edittext.setError("Provide your valid diu email");
        }
        if (PhoneValid) {
            student_phone = phone_edittext.getText().toString();
        } else {

            Toast.makeText(ActivityTeacherRegistration.this, "Provide a valid phone no please.", Toast.LENGTH_SHORT).show();
            //student_phone = "error-error-error";
            phone_edittext.setError("Provide a valid phone number");
        }


        if (teacher_full_name != null && teacher_designation != null && teacher_department!=null && IdValid && PhoneValid && EmailIsValid) {

            studentDatabaseHelper = new StudentDatabaseHelper(ActivityTeacherRegistration.this);
            sqLiteDatabase = studentDatabaseHelper.getWritableDatabase();

            boolean check = studentDatabaseHelper.InsertStudentInfo(teacher_full_name,student_id,teacher_department,teacher_designation,student_diu_email,student_phone,user_type,sqLiteDatabase);

            if (check) {
                introManager.setfirst(false);
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(ActivityTeacherRegistration.this)
                        .setTitle("Success!!")
                        .setMessage("thank you for providing your informations sir. to continue press ok.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(ActivityTeacherRegistration.this, Home.class);
                                startActivity(i);
                                finish();
                            }
                        });
                alertDialog2.show();
            } else {
                AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(ActivityTeacherRegistration.this)
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
