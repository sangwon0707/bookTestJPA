package com.example.demo.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="customer")
public class CustomerVO {
	@Id
	private int custid;
	
	private String name;
	private String addr;
	private String phone;
}
