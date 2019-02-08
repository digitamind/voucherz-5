package com.interswitch.discountvoucherz.api.model.distribution;

import com.interswitch.discountvoucherz.api.model.BaseEntity;
import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BulkVoucherDistribution extends BaseEntity {
    List<DiscountVoucher> voucherList;
}
