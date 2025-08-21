package com.example.animedxd.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.animedxd.R;
import com.example.animedxd.CarouselAdapter;
import com.example.animedxd.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class HomepageFragment extends Fragment {

    private String username;

    private TextView newsTab;
    private TextView mangaTab;
    private View newsCarouselContainer;
    private GridView mangaGridView;

    private ViewPager2 newsViewPager;
    private Handler handler;
    private Runnable runnable;
    private static final int DELAY_MS = 5000;

    private LinearLayout dotsLayout;
    private ImageView[] dots;


    private TextView welcomeLabel;
    private TextView usernameText;

    public static HomepageFragment newInstance(String username) {
        HomepageFragment fragment = new HomepageFragment();
        Bundle args = new Bundle();
        args.putString("username_key", username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username_key", "Pengguna");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsTab = view.findViewById(R.id.news_tab);
        mangaTab = view.findViewById(R.id.manga_tab);
        newsCarouselContainer = view.findViewById(R.id.news_carousel_container);
        mangaGridView = view.findViewById(R.id.manga_grid_view);

        // ✅ Inisialisasi welcome label dan username
        welcomeLabel = view.findViewById(R.id.welcome_label);
        usernameText = view.findViewById(R.id.username_text);

        if (username != null) {
            welcomeLabel.setText("Welcome,");
            usernameText.setText(username);
        }

        // Inisialisasi carousel untuk tab News
        List<CarouselItem> carouselItems = new ArrayList<>();
        carouselItems.add(new CarouselItem(R.drawable.demonslayer, "Demon Slayer: Kimetsu no Yaiba Infinity Castle Trilogy Confirmed!\n"));
        carouselItems.add(new CarouselItem(R.drawable.sololeveling, "A New Disappointing Update About ‘Solo Leveling’ Season 3\n"));
        carouselItems.add(new CarouselItem(R.drawable.jujutsu, "Jujutsu Kaisen Reveals New Satoru Gojo Visual to Mark Hidden Inventory / Premature Death\n"));

        newsViewPager = view.findViewById(R.id.news_view_pager);
        CarouselAdapter carouselAdapter = new CarouselAdapter(carouselItems);
        newsViewPager.setAdapter(carouselAdapter);

        newsViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateDotsIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    handler.removeCallbacks(runnable);
                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, DELAY_MS);
                }
            }
        });

        handler = new Handler(Looper.getMainLooper());
        runnable = () -> {
            int currentItem = newsViewPager.getCurrentItem();
            int nextItem = (currentItem + 1) % carouselItems.size();
            newsViewPager.setCurrentItem(nextItem, true);
            handler.postDelayed(runnable, DELAY_MS);
        };


        int[] mangaImageIds = {
                R.drawable.starofbeethoven,
                R.drawable.heroorganization,
                R.drawable.strikeout,
                R.drawable.yattara,
                R.drawable.exorcist,
        };

        String[] mangaTitles = {
                "Star of Beethoven",
                "Hero Organization",
                "Strikeout Pitch",
                "Yattara",
                "The Nito Exorcists",
        };

        String[] mangaAuthors = {
                "Morihiro Hayashi",
                "Kei Saikawa / Akira Takahashi",
                "Kyu Sumiyoshi",
                "Ooyamada",
                "Hiromi Ichikawa",
        };

        String[] mangaDescriptions = {
                "The door of fate now opens... Piano prodigy Ichiro Yaso left the world of classical music after an incident at a competition. He vowed to never play the piano again, but now that he has a mentor claiming to be Beethoven, it may be time for his redemption!",
                "In a near-future where humanity stands on the edge of extinction, we turn our gaze to the stars. But in the depths of space, a new menace looms—the 'Star Beasts.' To combat these creatures, warriors donning mechanical armor emerge as humanity's last hope. Prepare yourself for a groundbreaking mecha action saga, where breathtaking visuals meet an expansive universe. The battle for survival starts now!",
                "The Souha Public High School Baseball Team is one step away from the pinnacle that is the Summer Koushien. They only need a star pitcher to have a chance. Could the Baseball Club Slasher, the powerful pitcher who appears every night and terrorizes other players, be their guy? There's only 1 problem: he can only pitch 3 full-power balls a day!",
                "A gluttonous monster, Yattara, is drawn to a city of splendor and opulence. Its only pleasure in life: eating. It finds its favorite meal, children, and is ready to dig in as usual but then…a flash of inspiration strikes?",
                "Exorcists Gotsuji and Uruka spend their days exorcising evil spirits on request, inching ever closer to their goal: to cut down a specific spirit as a means of mourning the woman it killed. Uruka, mystified by Gotsuji's actions, accompanies him in his quest for revenge, but where will their journey lead...? Bunny outfits or traditional garb—doesn't matter! Just cut those spirits down!",

        };



        MangaGridViewAdapter mangaAdapter = new MangaGridViewAdapter(getContext(), mangaImageIds, mangaTitles, mangaAuthors, mangaDescriptions);
        mangaGridView.setAdapter(mangaAdapter);


        selectTab(newsTab, newsCarouselContainer);
        unselectTab(mangaTab, mangaGridView);
        handler.postDelayed(runnable, DELAY_MS);

        newsTab.setOnClickListener(v -> {
            selectTab(newsTab, newsCarouselContainer);
            unselectTab(mangaTab, mangaGridView);
            handler.postDelayed(runnable, DELAY_MS);
        });

        mangaTab.setOnClickListener(v -> {
            selectTab(mangaTab, mangaGridView);
            unselectTab(newsTab, newsCarouselContainer);
            handler.removeCallbacks(runnable);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (handler != null && runnable != null && newsViewPager.getVisibility() == View.VISIBLE) {
            handler.postDelayed(runnable, DELAY_MS);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    private void selectTab(TextView selectedTab, View selectedContent) {
        selectedTab.setBackgroundResource(R.drawable.tab_selcted_backgorund);
        selectedContent.setVisibility(View.VISIBLE);
    }

    private void unselectTab(TextView unselectedTab, View unselectedContent) {
        unselectedTab.setBackgroundResource(R.drawable.tab_unselected_backgorund);
        unselectedContent.setVisibility(View.GONE);
    }

    private void addDotsIndicator(int count) {
        dots = new ImageView[count];
        dotsLayout.removeAllViews();

        for (int i = 0; i < count; i++) {
            dots[i] = new ImageView(requireContext());
            dots[i].setImageResource(R.drawable.dot_inactive);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            dotsLayout.addView(dots[i], params);
        }
    }

    private void updateDotsIndicator(int position) {
        if (dots == null) return;
        for (int i = 0; i < dots.length; i++) {
            dots[i].setImageResource(i == position ? R.drawable.dot_active : R.drawable.dot_inactive);
        }
    }

    private class MangaGridViewAdapter extends BaseAdapter {

        private final Context context;
        private final int[] imageIds;
        private final String[] titles;
        private final String[] authors;
        private final String[] descriptions;

        public MangaGridViewAdapter(Context context, int[] imageIds, String[] titles, String[] authors, String[] descriptions) {
            this.context = context;
            this.imageIds = imageIds;
            this.titles = titles;
            this.authors = authors;
            this.descriptions = descriptions;
        }

        @Override
        public int getCount() {
            return imageIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View gridView;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                gridView = inflater.inflate(R.layout.mangalist_grid_item, null);
            } else {
                gridView = convertView;
            }

            ImageView imageView = gridView.findViewById(R.id.image_item);
            TextView titleTextView = gridView.findViewById(R.id.title_item);
            TextView authorTextView = gridView.findViewById(R.id.author_item);
            TextView descriptionTextView = gridView.findViewById(R.id.desc_item);

            imageView.setImageResource(imageIds[position]);
            titleTextView.setText(titles[position]);
            authorTextView.setText(authors[position]);
            descriptionTextView.setText(descriptions[position]);

            return gridView;
        }
    }
}


