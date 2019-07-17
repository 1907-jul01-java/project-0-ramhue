package com.revature.entities;

import java.util.List;

public interface DAO<E>{
    void insert(E e);

    List<E> getAll();

    void remove();

    void update(int a, double b);
}