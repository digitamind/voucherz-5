package com.interswitch.valuevoucherz.api.model.request;
import com.interswitch.voucherz.library.model.CodeConfig;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@ToString
@Getter
@Setter
public class BulkVouchers {
    private Voucher voucher;
    private CodeConfig codeConfig;
    private Integer quantity;
}
