package com.example.animedxd.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animedxd.DetailActivity;
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
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.animelist_grid_item, parent, false);
            holder = new ViewHolder();
            holder.image = convertView.findViewById(R.id.image_item);
            holder.title = convertView.findViewById(R.id.title_item);
            holder.genre = convertView.findViewById(R.id.genre_item);
            holder.synopsis = convertView.findViewById(R.id.synopsis_item);
            holder.btnViewDetail = convertView.findViewById(R.id.btn_view_detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.setImageResource(imageId[position]);
        holder.title.setText(titles[position]);
        holder.genre.setText(genres[position]);
        holder.synopsis.setText(synopsiss[position]);

        // Klik tombol View Detail
        holder.btnViewDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("image", imageId[position]);
            intent.putExtra("title", titles[position]);
            intent.putExtra("genre", genres[position]);
            intent.putExtra("synopsis", synopsiss[position]);
            context.startActivity(intent);
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView title, genre, synopsis, btnViewDetail;
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
