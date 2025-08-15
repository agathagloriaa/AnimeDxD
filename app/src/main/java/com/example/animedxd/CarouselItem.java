package com.example.animedxd;

public class CarouselItem {
    private int imageResId;
    private String title;

    public CarouselItem(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}
