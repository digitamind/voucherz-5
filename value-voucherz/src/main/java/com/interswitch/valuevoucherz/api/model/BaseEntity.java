package com.interswitch.valuevoucherz.api.model;

import lombok.Data;

import java.util.Objects;


@Data
public class BaseEntity {
    private long id;
    protected String merchantId;
    protected String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
