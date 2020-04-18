package com.company.Trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class SimpleQueue<T> implements Queue<T> {

    private ArrayList<T> list = new ArrayList<>();


    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public T remove() {
        return list.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
