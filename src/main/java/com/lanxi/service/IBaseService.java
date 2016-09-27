package com.lanxi.service;



import java.util.List;

/**
 * Created by summer on 16/8/20.
 */
public interface IBaseService<T> {
    List<T> getList(int offset, int count);
    T getById(int id);
    boolean update(T entity);
    boolean delete(int id);
    int add(T entity);
}
