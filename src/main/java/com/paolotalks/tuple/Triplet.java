package com.paolotalks.tuple;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Triplet<T, U, V> implements Serializable {

    public static <T, U, V> Triplet<T, V, U> pivot1(Triplet<T,U,V> triplet){
        return new Triplet<>(triplet.first, triplet.third, triplet.second);
    }

    public static <T, U, V> Triplet<V, U, T> pivot2(Triplet<T,U,V> triplet){
        return new Triplet<>(triplet.third, triplet.second, triplet.first);
    }

    public static <T, U, V> Triplet<U, T, V> pivot3(Triplet<T,U,V> triplet){
        return new Triplet<>(triplet.second, triplet.first, triplet.third);
    }

    public static <T extends Comparable<? super T>,
                   U extends Comparable<? super U>,
                   V extends Comparable<? super V>> Comparator<Triplet<T, U, V>> naturalComparator(){

        return Comparator.<Triplet<T, U, V>, T>comparing(Triplet::getFirst)
                .thenComparing(Triplet::getSecond)
                .thenComparing(Triplet::getThird);
    }

    private final T first;
    private final U second;
    private final V third;

    public Triplet(T first, U second, V third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }

    public int hashCode(){
        return Objects.hash(first, second, third);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) o;
        return Objects.equals(first, triplet.first) &&
                Objects.equals(second, triplet.second) &&
                Objects.equals(third, triplet.third) ;
    }
}
