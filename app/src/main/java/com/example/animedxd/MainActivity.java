package com.example.animedxd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.animedxd.fragment.HomepageFragment;
import com.example.animedxd.fragment.ListFragment;
import com.example.animedxd.fragment.AboutUsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private String username;


    private ImageView arrowIcon;
    private TextView logoutButton;
    private View darkOverlay;
    private boolean isLogoutMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username_key");
        }


        arrowIcon = findViewById(R.id.arrow_icon);
        logoutButton = findViewById(R.id.logout_button);
        darkOverlay = findViewById(R.id.overlay_view);


        if (logoutButton != null) logoutButton.setVisibility(View.GONE);
        if (darkOverlay != null) darkOverlay.setVisibility(View.GONE);

        if (arrowIcon != null && logoutButton != null && darkOverlay != null) {
            arrowIcon.setOnClickListener(v -> toggleLogoutMenu());


            darkOverlay.setOnClickListener(v -> hideLogoutMenu());


            logoutButton.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            });
        }


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        int itemId = item.getItemId();

                        if (itemId == R.id.nav_homepage) {
                            selectedFragment = HomepageFragment.newInstance(username);
                        } else if (itemId == R.id.nav_list) {
                            selectedFragment = new ListFragment();
                        } else if (itemId == R.id.nav_us) {
                            selectedFragment = AboutUsFragment.newInstance(username);
                        }

                        if (selectedFragment != null) {
                            replaceFragment(selectedFragment);
                        }
                        return true;
                    }
                }
        );


        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_homepage);
        }
    }

    private void toggleLogoutMenu() {
        if (isLogoutMenuVisible) {
            hideLogoutMenu();
        } else {
            showLogoutMenu();
        }
    }

    private void showLogoutMenu() {
        logoutButton.setVisibility(View.VISIBLE);
        darkOverlay.setVisibility(View.VISIBLE);
        arrowIcon.animate().rotation(180f).setDuration(200).start();
        isLogoutMenuVisible = true;
    }

    private void hideLogoutMenu() {
        logoutButton.setVisibility(View.GONE);
        darkOverlay.setVisibility(View.GONE);
        arrowIcon.animate().rotation(0f).setDuration(200).start();
        isLogoutMenuVisible = false;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
