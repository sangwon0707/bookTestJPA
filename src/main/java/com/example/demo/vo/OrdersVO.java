package com.example.demo.vo;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class OrdersVO {
	@Id
	private int orderid;

	@ManyToOne
	@JoinColumn(name="custid", insertable =true, updatable=true)
	private CustomerVO customer;
	
	@ManyToOne
	@JoinColumn(name="bookid", insertable =true, updatable=true)
	private BooksVO book;
	
	private int saleprice;
	private String orderdate;
}
