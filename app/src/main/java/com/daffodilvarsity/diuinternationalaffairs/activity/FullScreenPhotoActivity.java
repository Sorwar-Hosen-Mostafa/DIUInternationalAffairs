package com.daffodilvarsity.diuinternationalaffairs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.daffodilvarsity.diuinternationalaffairs.R;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoViewAttacher;

public class FullScreenPhotoActivity extends AppCompatActivity {

    ImageView imageView;
    PhotoViewAttacher photoViewAttacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_photo);
        imageView = (ImageView)findViewById(R.id.image);

        Intent i = getIntent();

        String image = i.getStringExtra("image");

        Picasso.with(this)
                .load(image)
                .into(imageView);

        photoViewAttacher = new PhotoViewAttacher(imageView);

    }
}
