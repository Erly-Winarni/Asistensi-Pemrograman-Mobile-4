package com.example.tuprak1;

public class HighlightItem {
    private String label;
    private int[] images;

    public HighlightItem(String label, int[] images) {
        this.label  = label;
        this.images = images;
    }

    public String getLabel()  { return label; }
    public int[]  getImages() { return images; }

    public int getCoverImage() { return images[0]; }
}