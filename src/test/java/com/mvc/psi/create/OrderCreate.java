package com.mvc.psi.create;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.psi.model.dto.OrderDto;
import com.mvc.psi.service.OrderService;

@SpringBootTest
public class OrderCreate {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void test() {
		Date date = new Date();
		orderService.add(date, 1L, 1L);
		
		System.out.println("Save OK");
		
	}

}
