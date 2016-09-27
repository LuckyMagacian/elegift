package com.lanxi.service.impl;

import com.lanxi.service.IBaseService;

import java.util.List;

/**
 * Created by summer on 16/8/21.
 */
public class BaseService<T> implements IBaseService<T> {
    @Override
    public List<T> getList(int offset, int count) {
        return null;
    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public boolean update(T entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public int add(T entity) {
        return 0;
    }
}
