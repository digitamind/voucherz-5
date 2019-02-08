package com.interswitch.voucherz.gift.api.model.request;
import com.interswitch.voucherz.gift.api.model.BaseEntity;
import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class GiftVoucher extends BaseEntity{
	private String code;
	private Integer amount;
	private Timestamp dateCreated;
	private Timestamp expiryDate;
	private Boolean isActive;
	private Boolean isCorporate;
	private String category;
	private String campaignId;
	private String customerId;
	private String productId;
	private String merchantStoreId;
	private String additionalInfo;
}
