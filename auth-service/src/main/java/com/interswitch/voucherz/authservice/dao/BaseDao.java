package com.interswitch.voucherz.authservice.dao;

import com.interswitch.voucherz.authservice.models.Page;

import java.util.List;

public interface BaseDao<T> {
    public T create(T Model);

    public boolean update(T model);

    public T find(long id);

    public List<T> findAll();

    public Page<T> findAll(int pageNumber, int pageSize);

    public boolean delete(T model);
}
