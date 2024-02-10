package com.mvc.psi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.CustomerDto;
import com.mvc.psi.model.dto.OrderDto;
import com.mvc.psi.model.po.Customer;
import com.mvc.psi.model.po.Employee;
import com.mvc.psi.model.po.Order;
import com.mvc.psi.repository.CustomerRepository;
import com.mvc.psi.repository.EmployeeRepository;
import com.mvc.psi.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// 新增
	public void add(OrderDto orderDto) {
		Order order = modelMapper.map(orderDto, Order.class);
		orderRepository.save(order);
	}
	
	public void add(Date date, Long customerId, Long employeeId) {
		Order order = new Order();
		
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		
		if(customerOpt.isPresent() && employeeOpt.isPresent()) {
			order.setDate(date);
			order.setCustomer(customerOpt.get());
			order.setEmployee(employeeOpt.get());
			orderRepository.save(order);
			
		}
	}
	
	// 修改
	public void update(OrderDto orderDto, Long id) {
		Optional<Order> orderOpt = orderRepository.findById(id);
		if(orderOpt.isPresent()) {
			Order order = modelMapper.map(orderDto, Order.class);
			orderRepository.save(order);
			return;
		}
		throw new RuntimeException("修改失敗, 無此資料");
	}
	
	// 刪除
	public void delete(Long id) {
		Optional<Order> orderOpt = orderRepository.findById(id);
		if(orderOpt.isPresent()) {
			orderRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗, 無此資料");
	}
	
	// 單筆查詢
	public OrderDto getOrderDtoById(Long id) {
		Optional<Order> orderOpt = orderRepository.findById(id);
		if(orderOpt.isPresent()) {
			return modelMapper.map(orderOpt.get(), OrderDto.class);
		}
		return null;
	}
	
	// 查詢全部
	public List<OrderDto> findAll() {
		return orderRepository.findAll().stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
	}

}
