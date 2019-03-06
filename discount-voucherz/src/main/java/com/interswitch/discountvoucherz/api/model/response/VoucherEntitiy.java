package com.interswitch.discountvoucherz.api.model.response;

import com.interswitch.discountvoucherz.api.model.request.DiscountType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class VoucherEntitiy{

	private String code;

	private Double value;

	private DiscountType type;

	private LocalDateTime dateCreated;

	private LocalDateTime expiryDate;

	private Boolean isActive;

	private String category;

	private String campaignId;

	private String customerId;

	private String productId;

	private String additionalInfo;

	public int getType(){
		return this.type.value();
	}
}
