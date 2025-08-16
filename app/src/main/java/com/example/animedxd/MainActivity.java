package com.example.animedxd;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.animedxd.fragment.HomepageFragment;
import com.example.animedxd.fragment.ListFragment;
import com.example.animedxd.fragment.AboutUsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menerima username dari Intent yang dikirim LoginActivity
        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username_key");
        }


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set listener untuk BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Definisikan fragment yang akan ditampilkan
                Fragment selectedFragment = null;

                // Cek ID item yang dipilih
                int itemId = item.getItemId();
                if (itemId == R.id.nav_homepage) {
                    selectedFragment = HomepageFragment.newInstance(username);
                } else if (itemId == R.id.nav_list) {
                    selectedFragment = new ListFragment();
                } else if (itemId == R.id.nav_us) {
                    selectedFragment = AboutUsFragment.newInstance(username);
                }

                // Ganti fragment di FrameLayout
                if (selectedFragment != null) {
                    replaceFragment(selectedFragment);
                }
                return true; // Mengembalikan true berarti event sudah ditangani
            }
        });

        // Set fragment default saat aplikasi pertama kali dibuka (misalnya HomepageFragment)
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_homepage); // Ini akan memicu listener di atas
        }
    }

    // Metode untuk mengganti fragment di FrameLayout
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}