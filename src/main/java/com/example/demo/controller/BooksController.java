package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.BooksDAO;
import com.example.demo.service.BooksService;
import com.example.demo.vo.BooksVO;
import com.example.demo.vo.CustomerVO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class BooksController {
	@Autowired
	private BooksService bs;
	
//	@GetMapping("/book/list")
//	public String search(@RequestParam String bookname, Model model){
//		String view = "redirect:/book/search";
//		List<BooksVO> b = bs.findByBookname(bookname);
//		model.addAttribute("b", b);
//		return view;
//	}
	
	@GetMapping("/book/delete")
	public String delete(int bookid){
		String view = "redirect:/book/listBook";
		bs.delete(bookid);
		return view;
	}
	
	@GetMapping("/book/update")
	private void update(int bookid, Model model){
		model.addAttribute("b", bs.findById(bookid));
	}
	
	@GetMapping("/book/detail")
	private void detail(int bookid, Model model){
		model.addAttribute("b",bs.findById(bookid));
	}
	
	@GetMapping("/book/list")
	private void list(Model model, String cname, String keyword, String sname, HttpSession session){
		HashMap<String, String> map = new HashMap<String, String>();
		
		String cname2 = null; 
		String keyword2 = null;
		
		if(session.getAttribute("keyword") != null){
			cname2= (String)session.getAttribute("cname");
			keyword2= (String)session.getAttribute("keyword");
		}
		
		if(keyword !=null){
			cname2 = cname;
			keyword2 = keyword;
			session.setAttribute("cname", cname);
			session.setAttribute("keyword", keyword);
		}
		
		map.put("cname", cname2);
		map.put("keyword", keyword);
		map.put("sname", sname);
		model.addAttribute("list", bs.findAll(map));
	}
	
	@PostMapping("/book/save")
	public String save(BooksVO b) {
		String view = "redirect:/listBook";
		bs.save(b);
		return view;
	}
	
}
