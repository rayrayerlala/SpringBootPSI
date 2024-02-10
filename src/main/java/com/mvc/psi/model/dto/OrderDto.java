package com.mvc.psi.model.dto;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.PostLoad;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	private CustomerDto customer;
	
	private EmployeeDto employee;
	
	private Set<OrderItemDto> orderItems = new LinkedHashSet<>();
	
	// 添加一個方法以動態計算總和
	public Integer calculateTotal() {
		return orderItems.stream()
				.mapToInt(orderItem -> orderItem.getProduct().getPrice() * orderItem.getAmount())
				.sum();
	}

	// 創建一個getter方法以在HTML中使用total
	public Integer getTotal() {
		return calculateTotal();
	}

}
