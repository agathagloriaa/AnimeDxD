package com.example.animedxd.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull; // Tambahkan import ini
import androidx.annotation.Nullable; // Tambahkan import ini
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animedxd.R;

public class AboutUsFragment extends Fragment {

    // View view; // Variabel ini tidak perlu dideklarasikan di sini jika langsung di-return

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_about_us layout
        // Perbaikan: Pastikan menginflasi layout yang benar
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }
}