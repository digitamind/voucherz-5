package com.interswitch.valuevoucherz.api.dao;

import com.interswitch.valuevoucherz.api.model.Page;
import org.springframework.stereotype.Component;

@Component
public interface BaseDao<T> {

    public T update(T model);

    public T find(String code);

    public Page<T> findAll(int pageNumber, int pageSize);

    public boolean delete(T voucher);
}
