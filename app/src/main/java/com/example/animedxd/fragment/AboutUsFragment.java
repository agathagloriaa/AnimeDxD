package com.example.animedxd.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull; // Tambahkan import ini
import androidx.annotation.Nullable; // Tambahkan import ini
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.animedxd.R;

public class AboutUsFragment extends Fragment {

    private static final String ARG_USERNAME = "username_key";
    private String username;

    public static AboutUsFragment newInstance(String username) {
        AboutUsFragment fragment = new AboutUsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_USERNAME, "User");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textWelcome = view.findViewById(R.id.text_welcome);
        textWelcome.setText("Welcome, " + username);
    }
}

