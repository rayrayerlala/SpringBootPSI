package com.mvc.psi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto {

	private Long id;
	
	private String name;

	@Override
	public String toString() {
		return "SupplierDto [id=" + id + ", name=" + name + "]";
	}
	
}
