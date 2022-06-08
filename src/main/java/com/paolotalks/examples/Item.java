package com.paolotalks.examples;

import java.util.Objects;

public class Item {

    private final int id;
    private String name;

    public Item(int id){
        this(id, null);
    }

    public Item(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int hashCode(){
        return Objects.hash(id);
    }

    public boolean equals(Object obj){
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Item oth = (Item) obj;
        return oth.id == this.id;
    }

    public String toString() {
        return "" + id;
    }
}
