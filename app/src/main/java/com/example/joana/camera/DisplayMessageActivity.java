package com.example.joana.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Joana on 20/03/2017.
 */

public class DisplayMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        Post message = (Post) intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = new TextView(this);
        textView.setText(message.getText());

        ImageView imageView = new ImageView(this);

        String photo = message.getPhoto();
        Bitmap bitmap = Encoding.decodeBase64(photo);
        imageView.setImageBitmap(bitmap);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);

        ViewGroup layoutImg = (ViewGroup) findViewById(R.id.activity_display_image);
        layoutImg.addView(imageView);
    }
}
