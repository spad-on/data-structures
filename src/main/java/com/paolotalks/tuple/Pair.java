package com.paolotalks.tuple;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Pair<T, U> implements Serializable {

    public static <T extends Comparable<? super T>, U> Comparator<Pair<T, U>> compareByFirst(){
        return Comparator.comparing(Pair::getFirst);
    }

    public static <T, U extends Comparable<? super U>> Comparator<Pair<T, U>> compareBySecond(){
        return Comparator.comparing(Pair::getSecond);
    }

    public static <T extends Comparable<? super T>, U extends Comparable<? super U>> Comparator<Pair<T, U>> naturalComparator(){
        return Comparator.<Pair<T, U>, T>comparing(Pair::getFirst).thenComparing(Pair::getSecond);
    }

    public static <T extends Comparable<? super T>, U extends Comparable<? super U>> Comparator<Pair<T, U>> reversedComparator(){
        return Comparator.<Pair<T, U>, U>comparing(Pair::getSecond).thenComparing(Pair::getFirst);
    }

    public static <T, U> Pair<U, T> invert(Pair<T, U> pair){
        return new Pair<>(pair.second, pair.first);
    }

    private final T first;
    private final U second;

    public Pair(T first, U second){
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public int hashCode(){
        return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
