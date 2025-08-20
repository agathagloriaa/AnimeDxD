package com.example.animedxd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {

    private List<CarouselItem> carouselItems;

    public CarouselAdapter(List<CarouselItem> carouselItems) {
        this.carouselItems = carouselItems;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carousel, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        CarouselItem item = carouselItems.get(position % carouselItems.size());
        holder.carouselImage.setImageResource(item.getImageResId());
        holder.carouselTitle.setText(item.getTitle());
    }


    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    static class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView carouselTitle;

        CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            carouselTitle = itemView.findViewById(R.id.carouselTitle);
        }
    }
}
