package com.interswitch.discountvoucherz.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Objects;


@Setter
@Getter
public class BaseEntity {
    protected long id;
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
