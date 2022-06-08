package com.paolotalks.heap;

import java.util.*;

public class HeapSet<V extends Comparable<? super V>> extends HeapMap<V, V> {

    public HeapSet(){
        super(null, s -> s);
    }

    public HeapSet(Comparator<? super V> cmp){
        super(cmp, s -> s);
    }

}
