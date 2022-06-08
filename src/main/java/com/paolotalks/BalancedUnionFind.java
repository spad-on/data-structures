package com.paolotalks;

import java.util.HashMap;
import java.util.Map;

/**
 * Union find implementation using
 * <i>union by rank</i> and <i>path compression</i>.
 */
public class BalancedUnionFind<E> implements UnionFind<E> {

    private Map<E, E> parent;
    private Map<E, Integer> ranks;
    private int numSets;

    public BalancedUnionFind() {
        this.parent = new HashMap<>();
        this.ranks = new HashMap<>();
    }

    @Override
    public E find(E i){
        E p = parent.get(i);
        if (p == null){
            throw new IllegalArgumentException("'" + i +"' does not exists");
        }
        if (p.equals(i)){
            return i;
        } else {
          parent.put(i, find(p));
          return parent.get(i);
        }
    }

    public boolean exists(E i){
        return parent.containsKey(i);
    }

    @Override
    public void makeSet(E i){
        if (exists(i)) return;
        parent.put(i, i);
        ranks.put(i, 0);
        numSets++;
        postMakeSet(i);
    }

    @Override
    public void union(E i, E j){
        E rootI = find(i);
        E rootJ = find(j);
        if (rootI == rootJ) return;
        numSets--;
        int rankI = ranks.get(rootI);
        int rankJ = ranks.get(rootJ);
        if ( rankI >= rankJ) {
            parent.put(rootJ, rootI);
            if (rankI == rankJ) ranks.merge(rootI, 1, Integer::sum);
            postUnion(rootJ, rootI);
        } else {
            parent.put(rootI, rootJ);
            postUnion(rootI, rootJ);
        }
    }

    @Override
    public int getComponents(){
        return numSets;
    }

    protected void postUnion(E from, E dest) {}

    protected void postMakeSet(E i) {}
}
