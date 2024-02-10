package com.mvc.psi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseItemDto {
	
	private Long id;
	
	private PurchaseDto purchase;
	
	private ProductDto product;
	
	private Integer amount;

}
