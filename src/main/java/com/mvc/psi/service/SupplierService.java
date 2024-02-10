package com.mvc.psi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.psi.model.dto.SupplierDto;
import com.mvc.psi.model.po.Supplier;
import com.mvc.psi.repository.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	@Transactional
	public void add(SupplierDto supplierDto) {
		Supplier supplier = modelMapper.map(supplierDto, Supplier.class);
		supplierRepository.save(supplier);
	}
	
	// 修改
	@Transactional
	public void update(SupplierDto supplierDto, Long id) {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		if(supplierOpt.isPresent()) {
			Supplier supplier = supplierOpt.get();
			supplier.setName(supplierDto.getName());
			supplierRepository.save(supplier);
			return;
		}
		throw new RuntimeException("修改失敗, 無此資料");
	}
	
	// 刪除
	@Transactional
	public void delete(Long id) {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		if(supplierOpt.isPresent()) {
			supplierRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗, 無此資料");
	}
	
	// 查詢單筆
	public SupplierDto getSupplierDtoById(Long id) {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		if(supplierOpt.isPresent()) {
			Supplier supplier = supplierOpt.get();
			
			SupplierDto supplierDto = modelMapper.map(supplier, SupplierDto.class);
			return supplierDto;
		}
		return null;
	}
	
	// 查詢全部
	public List<SupplierDto> findAll(){
		List<SupplierDto> supplierDtos = supplierRepository.findAll().stream()
				.map(supplier -> modelMapper.map(supplier, SupplierDto.class))
				.toList();
		return supplierDtos;
	}

}
