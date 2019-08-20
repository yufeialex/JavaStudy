package com.petrichor.java.zakka.picture;

/**
 * @author kodeyang
 */
public class ImageInfo {

    private int height;
    private int width;

    public ImageInfo(int height, int width) {
        super();
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "height: " + height + ", width: " + width;
    }
}
