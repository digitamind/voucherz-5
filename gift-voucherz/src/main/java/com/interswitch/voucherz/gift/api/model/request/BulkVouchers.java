package com.interswitch.voucherz.gift.api.model.request;
import com.interswitch.voucherz.library.model.CodeConfig;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@ToString
@Getter
@Setter
public class BulkVouchers {
    private GiftVoucher giftVoucher;
    private CodeConfig codeConfig;
    private Integer quantity;
}
