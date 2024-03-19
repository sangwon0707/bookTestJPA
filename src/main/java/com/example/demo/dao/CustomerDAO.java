package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.CustomerVO;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerVO, Integer> {
	//@Query("select nvl(max(custid),0)+1 from CustomerVO")
	@Query(value = "select nvl(max(custid),0)+1 from customer", nativeQuery = true)
	//@Query("SELECT COALESCE(MAX(c.custid), 0) + 1 FROM CustomerVO c")
	public int getNextNo();
	
	public CustomerVO findByName(String name);
	//select * from customer where name = name;
	//이름으로 검색할때 쓸수있을 거 같음
}
