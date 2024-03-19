package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrdersDAO;
import com.example.demo.vo.OrdersVO;

import lombok.Setter;

@Setter
@Service
public class OrdersService {
	@Autowired
	private OrdersDAO dao;
	
	public int getNextNo(){
		return dao.getNextNo();
	}
	
	public void insert(OrdersVO o){
		dao.insert(o);
	}
	
	
	//서비스 레이어(service layer)에서 파라미터 관련 로직을 처리하는 
	//첫 번째 접근 방식은 코드 구성과 유지 보수 면에서 일반적으로 더 좋습니다. 
	//이것은 관심사 분리와 모듈성을 유지하며 코드 테스트와 유지 관리를 용이하게 합니다. 
	//컨트롤러는 HTTP 요청을 처리하고 뷰 관리에 집중하며 
	//서비스와의 상호 작용을 담당하는 데 더 명확한 역할을 하게 됩니다.
	public List<OrdersVO> findAll(String keyword){
		if(keyword != null && !keyword.isEmpty()) {
			return dao.findByCustomer_Name(keyword);
		}
		else {
			return dao.findAll();
		}
	}
	
//	//컨트롤러단에서 구한기 위한 파인드올 메소
//	public List<OrdersVO> findAll(){
//		return dao.findAll();
//	}
//	//컨트롤러단에서 구현하기 위한 메소드
//	public List<OrdersVO> findByCustomer_Name(String name){
//		return dao.findByCustomer_NameOrderByOrderid(name);
//	}
}


