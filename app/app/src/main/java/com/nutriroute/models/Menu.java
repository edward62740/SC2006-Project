package com.nutriroute.models;

import java.util.List;

public class Menu {
    private List<MenuItem> items;
    private String id;

    public Menu(List<MenuItem> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public void clear() {
        items.clear();
    }

    public int size() {
        return items.size();
    }

    public MenuItem get(int index) {
        return items.get(index);
    }

    public void set(int index, MenuItem item) {
        items.set(index, item);
    }

    public boolean contains(MenuItem item) {
        return items.contains(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
