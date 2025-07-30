package com.example.animedxd.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animedxd.R;

public class AnimeGridViewAdapter extends BaseAdapter {
    private Context context;
    private int[] imageId;
    private String[] titles;
    private String[] genres;
    private String[] synopsiss;

    public AnimeGridViewAdapter(Context context, int[] imageId, String[] titles, String[] genres, String[] synopsiss){
        this.imageId = imageId;
        this.context = context;
        this.titles = titles;
        this.genres = genres;
        this.synopsiss = synopsiss;
    }

    @Override
    public int getCount() {
        return imageId.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.animelist_grid_item, parent, false);
        }
        ImageView image = convertView.findViewById(R.id.image_item);
        TextView title = convertView.findViewById(R.id.title_item);
        TextView genre = convertView.findViewById(R.id.genre_item);
        TextView synopsis = convertView.findViewById(R.id.synopsis_item);

        image.setImageResource(imageId[position]);
        title.setText(titles[position]);
        genre.setText(genres[position]);
        synopsis.setText(synopsiss[position]);

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
