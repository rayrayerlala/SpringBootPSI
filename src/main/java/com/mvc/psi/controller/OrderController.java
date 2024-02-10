package com.mvc.psi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.psi.model.dto.CustomerDto;
import com.mvc.psi.model.dto.EmployeeDto;
import com.mvc.psi.model.dto.OrderDto;
import com.mvc.psi.model.dto.OrderItemDto;
import com.mvc.psi.model.dto.ProductDto;
import com.mvc.psi.service.CustomerService;
import com.mvc.psi.service.EmployeeService;
import com.mvc.psi.service.OrderItemService;
import com.mvc.psi.service.OrderService;
import com.mvc.psi.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String index(@ModelAttribute OrderDto orderDto, Model model) {
		List<OrderDto> orderDtos = orderService.findAll();
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		List<CustomerDto> customerDtos = customerService.findAll();
		model.addAttribute("customerDtos",customerDtos);
		model.addAttribute("employeeDtos",employeeDtos);
		model.addAttribute("orderDtos",orderDtos);
		return "order";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		OrderDto orderDto = orderService.getOrderDtoById(id);
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		List<CustomerDto> customerDtos = customerService.findAll();
		model.addAttribute("orderDto",orderDto);
		model.addAttribute("employeeDtos",employeeDtos);
		model.addAttribute("customerDtos",customerDtos);
		return "order-edit";		
	}
	
	@PostMapping("/")
	public String add(OrderDto orderDto) {
		orderService.add(orderDto);
		return "redirect:/order/";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, OrderDto orderDto) {
		orderService.update(orderDto, id);
		return "redirect:/order/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		orderService.delete(id);
		return "redirect:/order/";
	}
	
	@GetMapping("/{oid}/item")
	public String item_index(@PathVariable("oid") Long oid, @ModelAttribute OrderItemDto orderItemDto, Model model) {
		OrderDto orderDto = orderService.getOrderDtoById(oid);
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		List<CustomerDto> customerDtos = customerService.findAll();
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("orderDto",orderDto);
		model.addAttribute("employeeDtos",employeeDtos);
		model.addAttribute("customerDtos",customerDtos);
		model.addAttribute("productDtos",productDtos);
		return "order-item";
	}
	
	@PostMapping("/{oid}/item")
	public String item_add(@PathVariable("oid") Long oid, OrderItemDto orderItemDto) {
		OrderDto orderDto = orderService.getOrderDtoById(oid);
		orderItemDto.setOrder(orderDto);
		orderItemService.add(orderItemDto);
		return "redirect:/order/{oid}/item";
	}
	
	@PutMapping("/{oid}/item")
	public String item_update(@PathVariable("oid") Long oid, OrderItemDto orderItemDto, @RequestParam("id") Long id) {
		OrderDto orderDto = orderService.getOrderDtoById(oid);
		orderItemDto.setOrder(orderDto);
		orderItemService.update(orderItemDto, id);
		return "redirect:/order/{oid}/item";
	}
	
	@GetMapping("/edit/{oid}/item/{id}")
	public String item_edit(@PathVariable("oid") Long oid, Model model, @PathVariable("id") Long id) {
		OrderDto orderDto = orderService.getOrderDtoById(oid);
		OrderItemDto orderItemDto = orderItemService.getOrderItemDtoById(id);
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		List<CustomerDto> customerDtos = customerService.findAll();
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("orderItemDto",orderItemDto);
		model.addAttribute("orderDto",orderDto);
		model.addAttribute("employeeDtos",employeeDtos);
		model.addAttribute("customerDtos",customerDtos);
		model.addAttribute("productDtos",productDtos);
		return "order-item";
	}
	
	@GetMapping("/delete/{oid}/item/{id}")
	public String item_delete(@PathVariable("id") Long id) {
		orderItemService.delete(id);
		return "redirect:/order/{oid}/item";
	}
	

}
