package com.interswitch.discountvoucherz.api.model.request;
import com.interswitch.voucherz.library.model.CodeConfig;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Getter
@Setter
public class BulkVouchers{
    private DiscountVoucher discountVoucher;
    private CodeConfig codeConfig;
    private Integer quantity;
}
