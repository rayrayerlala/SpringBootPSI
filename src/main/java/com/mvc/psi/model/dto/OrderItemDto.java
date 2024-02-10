package com.mvc.psi.model.dto;

import com.mvc.psi.model.po.Order;
import com.mvc.psi.model.po.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
	
	private Long id;

	private OrderDto order;

	private ProductDto product;
	
	private Integer amount;

}
