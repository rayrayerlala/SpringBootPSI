package com.mvc.psi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.OrderItemDto;
import com.mvc.psi.model.po.OrderItem;
import com.mvc.psi.repository.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	public void add(OrderItemDto orderItemDto) {
		OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
		orderItemRepository.save(orderItem);
	}
	
	// 修改
	public void update(OrderItemDto orderItemDto, Long id) {
		Optional<OrderItem> orderItemOpt = orderItemRepository.findById(id);
		if(orderItemOpt.isPresent()) {
			OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
			orderItemRepository.save(orderItem);
			return;
		}
		throw new RuntimeException("修改失敗, 無此資料");
	}
	
	// 刪除
	public void delete(Long id) {
		Optional<OrderItem> orderItemOpt = orderItemRepository.findById(id);
		if(orderItemOpt.isPresent()) {
			orderItemRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗, 無此資料");
	}
	
	// 查詢單筆
	public OrderItemDto getOrderItemDtoById(Long id) {
		Optional<OrderItem> orderItemOpt = orderItemRepository.findById(id);
		if(orderItemOpt.isPresent()) {
			return modelMapper.map(orderItemOpt.get(), OrderItemDto.class);
		}
		return null;
	}
	
	// 查詢全部
	public List<OrderItemDto> findAll(){
		return orderItemRepository.findAll().stream().map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class)).toList();
	}

}
