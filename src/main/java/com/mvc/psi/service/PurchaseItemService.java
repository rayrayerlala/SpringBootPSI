package com.mvc.psi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.PurchaseItemDto;
import com.mvc.psi.model.po.PurchaseItem;
import com.mvc.psi.repository.PurchaseItemRepository;

@Service
public class PurchaseItemService {
	
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	public void add(PurchaseItemDto purchaseItemDto) {
		PurchaseItem purchaseItem = modelMapper.map(purchaseItemDto, PurchaseItem.class);
		purchaseItemRepository.save(purchaseItem);
	}
	
	// 修改
	public void update(PurchaseItemDto purchaseItemDto, Long id) {
		Optional<PurchaseItem> purchaseItemOpt = purchaseItemRepository.findById(id);
		if(purchaseItemOpt.isPresent()) {
			PurchaseItem purchaseItem = modelMapper.map(purchaseItemDto, PurchaseItem.class);
			purchaseItemRepository.save(purchaseItem);
			return;
		}
		throw new RuntimeException("修改失敗, 無此資料");
	}
	
	// 刪除
	public void delete(Long id) {
		Optional<PurchaseItem> purchaseItemOpt = purchaseItemRepository.findById(id);
		if(purchaseItemOpt.isPresent()) {
			purchaseItemRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗, 無此資料");
	}
	
	// 查詢單筆
	public PurchaseItemDto getPurchaseDtoById(Long id) {
		Optional<PurchaseItem> purchaseItemOpt = purchaseItemRepository.findById(id);
		if(purchaseItemOpt.isPresent()) {
			return modelMapper.map(purchaseItemOpt.get(), PurchaseItemDto.class);
		}
		return null;
	}
	
	// 查詢全部
	public List<PurchaseItemDto> findAll(){
		return purchaseItemRepository.findAll().stream()
				.map(purchaseItem -> modelMapper.map(purchaseItem, PurchaseItemDto.class))
				.toList();
	}

}
