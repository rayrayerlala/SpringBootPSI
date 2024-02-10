package com.mvc.psi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.psi.model.dto.ProductDto;
import com.mvc.psi.model.po.Product;
import com.mvc.psi.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 新增
	@Transactional
	public void add(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		productRepository.save(product);
	}
	
	// 修改
	@Transactional
	public void update(ProductDto productDto, Long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			product.setName(productDto.getName());
			product.setCost(productDto.getCost());
			product.setPrice(productDto.getPrice());
			productRepository.save(product);
			return;
		}
		throw new RuntimeException("修改失敗, 無此資料");
	}
	
	// 刪除
	@Transactional
	public void delete(Long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		if(productOpt.isPresent()) {
			productRepository.deleteById(id);
			return;
		}
		throw new RuntimeException("刪除失敗, 無此資料");
	}
	
	// 查詢單筆
	public ProductDto getProductDtoById(Long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		if(productOpt.isPresent()) {
			ProductDto productDto = modelMapper.map(productOpt.get(), ProductDto.class);
			return productDto;
		}
		return null;
	}
	
	// 查詢全部
	public List<ProductDto> findAll(){
		return productRepository.findAll().stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.toList();
	}
	
}
