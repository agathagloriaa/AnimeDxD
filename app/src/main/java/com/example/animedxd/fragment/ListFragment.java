package com.example.animedxd.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.animedxd.R;

public class ListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1. Inflate layout untuk fragment terlebih dahulu
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // GridView Data
        int[] imageId = {
            R.drawable.img1_darling_in_the_franxx,
            R.drawable.img2_5_toubun_no_hanayome,
            R.drawable.img3_ansatsu_kyoushitsu,
            R.drawable.img4_horimiya,
            R.drawable.img5_rezero_kara_hajimeru_isekai_seikatsu,
            R.drawable.img6_satsuriku_no_tenshi,
            R.drawable.img7_yakusoku_no_neverland,
        };

        String[] titles = {
            "Darling in the FranXX",
            "5-toubun no Hanayome",
            "Ansatsu Kyoushitsu",
            "Horimiya",
            "Re:Zero kara Hajimeru Isekai Seikatsu",
            "Satsuriku no Tenshi",
            "Yakusoku no Neverland"
        };

        String[] genres = {
                "Mecha, Romantic Drama",
                "Harem, Romantic Comedy",
                "Action, Science Fiction, Comedy",
                "Romantic Comedy",
                "Adventure, Dark Fantasy, Isekai",
                "Psychological Horror",
                "Dark Fantasy, Science Fiction, Thriller",
        };

        String[] synopsiss = {
            "In a world at war with Klaxosaurs, Hiro meets mysterious Zero Two. Together, they pilot a FranXX, giving Hiro purpose—at a deadly cost.",
            "As Fuutarou tutors the quintuplets, their grades—and feelings—grow, blurring the line between teacher and students.",
            "Class 3-E must kill their superpowered teacher Koro-sensei before he destroys Earth—if they can, since he’s the best teacher they've ever had.",
            "Hori and Miyamura seem like opposites, but outside school, they grow close by sharing hidden sides.",
            "Subaru is transported to a fantasy world, where he's killed and repeatedly resurrected, stuck in a time loop with no way out.",
            "Rachel and Zack, a murderer, try to escape a deadly building while uncovering Rachel's connection to the place.",
            "At Grace Field, orphans uncover the dark truth behind adoptions—and their beloved Mama’s secret.",
        };

        // 2. Cari GridView dari objek 'view' yang sudah di-inflate
        GridView animeGridView = view.findViewById(R.id.animelist_grid_view);

        // 3. Buat adapter dan berikan semua parameter yang dibutuhkan
        AnimeGridViewAdapter adapter = new AnimeGridViewAdapter(getContext(), imageId, titles, genres, synopsiss);
        animeGridView.setAdapter(adapter);

        // Kembalikan objek 'view' yang sudah di-inflate dan diisi data
        return view;
    }
}