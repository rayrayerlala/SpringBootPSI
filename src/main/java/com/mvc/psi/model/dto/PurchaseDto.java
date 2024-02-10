package com.mvc.psi.model.dto;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDto {
	
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	private SupplierDto supplier;
	
	private EmployeeDto employee;
	
	private Set<PurchaseItemDto> purchaseItems = new LinkedHashSet<>();
	
	public Integer getTotal() {
		return purchaseItems.stream()
				.mapToInt(purchaseItem -> purchaseItem.getProduct().getCost() * purchaseItem.getAmount())
				.sum();
	}

}
