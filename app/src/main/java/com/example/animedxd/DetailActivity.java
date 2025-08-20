package com.example.animedxd;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView tvErrorReview;

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

        Button btnReview = findViewById(R.id.btn_review);
        btnReview.setOnClickListener(v -> {
            // Buat dialog
            Dialog dialog = new Dialog(DetailActivity.this);
            dialog.setContentView(R.layout.dialog_review);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // biar rounded

            EditText inputReview = dialog.findViewById(R.id.input_review);
            TextView tvErrorReview = dialog.findViewById(R.id.tvErrorReview); // <-- ambil dari dialog!
            Button btnSubmit = dialog.findViewById(R.id.btn_submit_review);

            btnSubmit.setOnClickListener(view -> {
                String review = inputReview.getText().toString().trim();
                boolean isValid = true;

                // --- Review Validation ---
                if (TextUtils.isEmpty(review)) {
                    tvErrorReview.setText("Review must be filled in");
                    tvErrorReview.setVisibility(View.VISIBLE);
                    isValid = false;
                } else {
                    tvErrorReview.setVisibility(View.INVISIBLE);
                }

                if (!isValid) return;


                dialog.dismiss(); // tutup setelah sukses submit
            });

            dialog.show();
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT, // lebar full
                    ViewGroup.LayoutParams.WRAP_CONTENT  // tinggi menyesuaikan
            );
        });


    }


}
