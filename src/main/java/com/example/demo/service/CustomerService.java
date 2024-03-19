package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.CustomerVO;

@Service
public class CustomerService {
	@Autowired
	private CustomerDAO dao;
	
	public CustomerVO findByName(String name){
		return dao.findByName(name);
	}
	
	public int getNextNo(){
		return dao.getNextNo();
	}
	
	public List<CustomerVO> findAll(){
		return dao.findAll();
	}
	
	public void save(CustomerVO c) {
		dao.save(c);
	}
	
	public CustomerVO getCustomer(int custid){
		return dao.getOne(custid);
	}
	
	public void delete(int custid){
		dao.deleteById(custid);
	}
	

}
