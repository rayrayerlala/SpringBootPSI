package com.mvc.psi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.psi.model.dto.PurchaseDto;
import com.mvc.psi.model.po.Purchase;
import com.mvc.psi.repository.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	public void add(PurchaseDto purchaseDto) {
		Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
		purchaseRepository.save(purchase);
	}
	
	// 修改
	public void update(PurchaseDto purchaseDto, Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			Purchase purchase = modelMapper.map(purchaseDto, Purchase.class);
			purchaseRepository.save(purchase);
			return;
		}
		throw new RuntimeException("修改失敗, 無此資料");
	}
	
	// 刪除
	public void delete(Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			purchaseRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗, 無此資料");
	}
	
	// 查詢單筆
	public PurchaseDto getPurchaseDtoById(Long id) {
		Optional<Purchase> purchaseOpt = purchaseRepository.findById(id);
		if(purchaseOpt.isPresent()) {
			return modelMapper.map(purchaseOpt.get(), PurchaseDto.class);
		}
		return null;
	}
	
	// 查詢全部
	public List<PurchaseDto> findAll(){
		return purchaseRepository.findAll().stream().map(purchase -> modelMapper.map(purchase, PurchaseDto.class)).toList();
	}

}
