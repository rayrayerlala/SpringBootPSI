package com.mvc.psi.model.po;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Employee employee;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private Set<OrderItem> orderItems = new LinkedHashSet<>();
	
}
