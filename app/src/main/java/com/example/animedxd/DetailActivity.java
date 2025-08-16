package com.example.animedxd;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.detail_image);
        TextView titleView = findViewById(R.id.detail_title);
        TextView genreView = findViewById(R.id.detail_genre);
        TextView synopsisView = findViewById(R.id.detail_synopsis);

        // Ambil data dari Intent
        int imageRes = getIntent().getIntExtra("image", 0);
        String title = getIntent().getStringExtra("title");
        String genre = getIntent().getStringExtra("genre");
        String synopsis = getIntent().getStringExtra("synopsis");

        // Set data
        imageView.setImageResource(imageRes);
        titleView.setText(title);
        genreView.setText(genre);
        synopsisView.setText(synopsis);

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
    }

}
