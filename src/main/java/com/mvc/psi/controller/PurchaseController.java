package com.mvc.psi.controller;

import java.util.List;

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

import com.mvc.psi.model.dto.EmployeeDto;
import com.mvc.psi.model.dto.ProductDto;
import com.mvc.psi.model.dto.PurchaseDto;
import com.mvc.psi.model.dto.PurchaseItemDto;
import com.mvc.psi.model.dto.SupplierDto;
import com.mvc.psi.service.EmployeeService;
import com.mvc.psi.service.ProductService;
import com.mvc.psi.service.PurchaseItemService;
import com.mvc.psi.service.PurchaseService;
import com.mvc.psi.service.SupplierService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PurchaseItemService purchaseItemService;
	
	@GetMapping("/")
	public String index(@ModelAttribute PurchaseDto purchaseDto, Model model) {
		List<PurchaseDto> purchaseDtos = purchaseService.findAll();
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		List<SupplierDto> supplierDtos = supplierService.findAll();
		model.addAttribute("purchaseDtos",purchaseDtos);
		model.addAttribute("employeeDtos",employeeDtos);
		model.addAttribute("supplierDtos",supplierDtos);
		return "purchase";
	}
	
	@PostMapping("/")
	public String add(PurchaseDto purchaseDto) {
		purchaseService.add(purchaseDto);
		return "redirect:/purchase/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		PurchaseDto purchaseDto = purchaseService.getPurchaseDtoById(id);
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		List<SupplierDto> supplierDtos = supplierService.findAll();
		model.addAttribute("purchaseDto",purchaseDto);
		model.addAttribute("employeeDtos",employeeDtos);
		model.addAttribute("supplierDtos",supplierDtos);
		return "purchase-edit";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, PurchaseDto purchaseDto) {
		purchaseService.update(purchaseDto, id);
		return "redirect:/purchase/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		purchaseService.delete(id);
		return "redirect:/purchase/";
	}
	
	@GetMapping("/{pid}/item")
	public String item_index(@PathVariable("pid") Long pid, Model model, @ModelAttribute PurchaseItemDto purchaseItemDto) {
		PurchaseDto purchaseDto = purchaseService.getPurchaseDtoById(pid);
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("purchaseDto",purchaseDto);
		model.addAttribute("productDtos",productDtos);
		return "purchase-item";
	}
	
	@PostMapping("/{pid}/item")
	public String item_add(@PathVariable("pid") Long pid, PurchaseItemDto purchaseItemDto) {
		PurchaseDto purchaseDto = purchaseService.getPurchaseDtoById(pid);
		purchaseItemDto.setPurchase(purchaseDto);
		purchaseItemService.add(purchaseItemDto);
		return "redirect:/purchase/{pid}/item";
	}
	
	@GetMapping("/edit/{pid}/item/{id}")
	public String item_edit(@PathVariable("pid") Long pid, Model model, @PathVariable("id") Long id) {
		PurchaseItemDto purchaseItemDto = purchaseItemService.getPurchaseDtoById(id);
		PurchaseDto purchaseDto = purchaseService.getPurchaseDtoById(pid);
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("purchaseItemDto",purchaseItemDto);
		model.addAttribute("purchaseDto",purchaseDto);
		model.addAttribute("productDtos",productDtos);
		return "purchase-item";
	}
	
	@PutMapping("/{pid}/item")
	public String item_update(@PathVariable("pid") Long pid, PurchaseItemDto purchaseItemDto, @RequestParam("id") Long id) {
		PurchaseDto purchaseDto = purchaseService.getPurchaseDtoById(pid);
		purchaseItemDto.setPurchase(purchaseDto);
		purchaseItemService.update(purchaseItemDto, id);
		return "redirect:/purchase/{pid}/item";
	}
	
	@GetMapping("/delete/{pid}/item/{id}")
	public String item_delete(@PathVariable("id") Long id) {
		purchaseItemService.delete(id);
		return "redirect:/purchase/{pid}/item";
	}
	
}
