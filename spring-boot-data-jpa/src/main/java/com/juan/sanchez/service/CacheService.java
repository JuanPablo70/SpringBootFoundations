package com.juan.sanchez.service;

public interface CacheService<T, ID> {

    boolean add(T t);
    T get(ID id);
    boolean delete(ID id);
    void report();

}
