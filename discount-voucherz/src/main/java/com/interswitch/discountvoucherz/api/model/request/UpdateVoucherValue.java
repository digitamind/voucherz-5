package com.interswitch.discountvoucherz.api.model.request;

import com.interswitch.discountvoucherz.api.model.BaseEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Setter
public class UpdateVoucherValue extends BaseEntity{
    private Double value;
    private String code;
}
