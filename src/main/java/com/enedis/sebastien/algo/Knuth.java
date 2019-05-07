package com.enedis.sebastien.algo;

public interface Knuth {
    interface Fonction<S, T> {
        T appelle (S x);
    }
}
