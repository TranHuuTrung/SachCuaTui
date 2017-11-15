package com.huutrung.sachcuatui.Book;

/**
 * Created by Admin on 11/1/2017.
 */


public class Book {
    private int imageView;
    private String textView;
    private int ratingBar;

    public Book(int imageView, String textView, int ratingBar) {
        this.imageView = imageView;
        this.textView = textView;
        this.ratingBar = ratingBar;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }

    public int getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(int ratingBar) {
        this.ratingBar = ratingBar;
    }
}