package com.yufei.languagebasic.concurrent.util.cylicBarrier2;

/**
 * Created by XinYufei on 2018/1/8.
 */
public class Results {
    private int[] data;

    public Results(int size) {
        data = new int[size];
    }

    public void setData(int position, int value) {
        data[position] = value;
    }

    public int[] getData() {
        return data;
    }
}
