package com.interswitch.valuevoucherz.api.model.request;

import com.interswitch.valuevoucherz.api.model.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@ToString
public class Voucher extends BaseEntity {
	private String code;
	private BigInteger amount;
	private Timestamp dateCreated;
	private Timestamp expiryDate;
	private Boolean isActive;
	private Boolean isCorporate;
	private String category;
	private String campaignId;
	private String customerId;
	private String merchantStoreId;
	private String additionalInfo;
}
