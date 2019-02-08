package com.interswitch.voucherz.gift.api.model.distribution;

import com.interswitch.voucherz.gift.api.model.BaseEntity;
import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BulkVoucherDistribution extends BaseEntity {
    List<GiftVoucher> voucherList;
}
