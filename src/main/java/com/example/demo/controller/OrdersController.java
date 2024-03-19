package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.BooksService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrdersService;
import com.example.demo.vo.OrdersVO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class OrdersController {
	@Autowired
	private BooksService bs;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private OrdersService os;
	
	//컨트롤러단에서 구현하기 
//	@GetMapping("/orders/list")
//	public void findAll(String keyword, Model model){
//		System.out.println(keyword);
//		if(keyword != null && !keyword.isEmpty()){
//			model.addAttribute("list", os.findByCustomer_Name(keyword));
//		}else {
//			model.addAttribute("list", os.findAll());
//		}
//	}
	
	//서비스 레이어(service layer)에서 파라미터 관련 로직을 처리하는 
	//첫 번째 접근 방식은 코드 구성과 유지 보수 면에서 일반적으로 더 좋습니다. 
	//이것은 관심사 분리와 모듈성을 유지하며 코드 테스트와 유지 관리를 용이하게 합니다. 
	//컨트롤러는 HTTP 요청을 처리하고 뷰 관리에 집중하며 
	//서비스와의 상호 작용을 담당하는 데 더 명확한 역할을 하게 됩니다.
	
	@GetMapping("/orders/list")
	public void list(Model model, String keyword) {
		model.addAttribute("list", os.findAll(keyword));
	}
	
	@PostMapping("/orders/save")
	public String save(OrdersVO o) {
		String view = "redirect:/orders/insert";
		os.insert(o);
		return view;
	}
	
	@GetMapping("/orders/insert")
	public void insert(Model model){
		//model.addAttribute("bList", bs.findAll());
		model.addAttribute("bList", bs.findAll(null));
		model.addAttribute("cList", cs.findAll());
		model.addAttribute("orderid", os.getNextNo());
	}
}
