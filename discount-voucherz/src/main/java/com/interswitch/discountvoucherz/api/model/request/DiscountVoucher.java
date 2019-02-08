package com.interswitch.discountvoucherz.api.model.request;

import com.interswitch.discountvoucherz.api.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class DiscountVoucher extends BaseEntity {
	@NotBlank(message = "Required")
	@Length(min = 2, max = 16)
	private String code;

	@NotBlank(message = "Required")
	private Integer value;

	@NotBlank(message = "Required")
	private DiscountType type;

	private Timestamp dateCreated;

	private Timestamp expiryDate;

	@NotBlank(message = "Required")
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
