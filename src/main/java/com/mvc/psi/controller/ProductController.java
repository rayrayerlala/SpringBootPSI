package com.mvc.psi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.psi.model.dto.ProductDto;
import com.mvc.psi.service.ProductService;
import com.mvc.psi.validator.ProductValidator;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductValidator productValidator;
	
	@GetMapping("/")
	public String index(@ModelAttribute ProductDto productDto, Model model) {
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("productDtos",productDtos);
		return "product";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		ProductDto productDto = productService.getProductDtoById(id);
		model.addAttribute("productDto",productDto);
		return "product-edit";
	}
	
	@PostMapping("/")
	public String add(ProductDto productDto, BindingResult result, Model model) {
		// 驗證資料(驗證標的: productDto, 驗證結果 result)
		productValidator.validate(productDto, result);
		if(result.hasErrors()) {
			// 重新將資料與錯誤訊息回傳給 product.html
			model.addAttribute("productDto", productDto);
			model.addAttribute("productDtos", productService.findAll());
			// 錯誤訊息會自動帶入, 不需要手動帶入
			return "product";
		}
		
		productService.add(productDto);
		return "redirect:/product/";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, ProductDto productDto, BindingResult result, Model model) {
		// 驗證資料(驗證標的: productDto, 驗證結果 result)
		productValidator.validate(productDto, result);
		if(result.hasErrors()) {
			// 重新將資料與錯誤訊息回傳給 product.html
			model.addAttribute("productDto", productDto);
			model.addAttribute("productDtos", productService.findAll());
			// 錯誤訊息會自動帶入, 不需要手動帶入
			return "product-edit";
		}
		
		productService.update(productDto, id);
		return "redirect:/product/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		productService.delete(id);
		return "redirect:/product/";
	}
}
