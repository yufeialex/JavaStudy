package com.petrichor.java.language.collection.convert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class List2Set {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        Set<Integer> ids = new HashSet<>(a);
    }

}
