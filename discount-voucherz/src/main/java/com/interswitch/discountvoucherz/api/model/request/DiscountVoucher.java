package com.interswitch.discountvoucherz.api.model.request;

import com.interswitch.discountvoucherz.api.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@ToString
public class DiscountVoucher extends BaseEntity {

	private String code;

	private Double value;

	private DiscountType type;

	private LocalDate dateCreated;

	private LocalDate expiryDate;

	private Boolean isActive;

	private String category;

	private String campaignId;

	private String customerId;

	private String productId;

	private String additionalInfo;

	public int getType(){
		return this.type.value();
	}

	public void setExpiryDate(String date){
		this.expiryDate = LocalDate.parse(date);

	}
}
