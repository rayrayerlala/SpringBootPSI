package com.mvc.psi.model.po;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "purchase")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@ManyToOne
	private Supplier supplier;
	
	@ManyToOne
	private Employee employee;
	
	@OneToMany(mappedBy = "purchase")
	private Set<PurchaseItem> purchaseItems;

}
