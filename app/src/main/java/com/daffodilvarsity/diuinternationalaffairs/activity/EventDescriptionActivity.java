package com.daffodilvarsity.diuinternationalaffairs.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daffodilvarsity.diuinternationalaffairs.R;
import com.squareup.picasso.Picasso;

public class EventDescriptionActivity extends AppCompatActivity {

    ImageView eventimage;
    TextView eventTitle,eventDescription,eventDeadline;
    ImageButton showImage;
    Button instantApply;
    private Intent i;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);

        eventimage = (ImageView) findViewById(R.id.Eventimage);
        eventTitle= (TextView) findViewById(R.id.eventtitle);
        eventDescription = (TextView) findViewById(R.id.Eventdescription);
        eventDeadline = (TextView) findViewById(R.id.EventDeadline);
        showImage = (ImageButton) findViewById(R.id.imageButton);
        instantApply = (Button) findViewById(R.id.instantApplybutton);


        instantApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EventDescriptionActivity.this);
                alert.setTitle("Apply!");
                alert.setMessage("Are you sure you want to apply for this event?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(EventDescriptionActivity.this, "Application for this event is successful.", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No",null);
                alert.show();

            }
        });

        i = getIntent();
        String  title = i.getStringExtra("title");
        image = i.getStringExtra("image");
        String deadline = i.getStringExtra("deadline");
        String description = i.getStringExtra("description");

        eventTitle.setText(title);

        Picasso.with(this)
                .load(image)
                .into(eventimage);

        eventDeadline.setText(deadline);

        eventDescription.setText(description);

        showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventDescriptionActivity.this,FullScreenPhotoActivity.class);
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });



    }
}
