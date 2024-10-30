package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Mobile;
import com.example.demo.repository.MobileRepository;

@Controller
public class MobileController {
	@Autowired MobileRepository mobileRep;
	
	@GetMapping("")
	public String index() {
		return "index";
	}
	
	@GetMapping("display")
	public String display(Model model) {
		List<Mobile> MobileList = mobileRep.findAll();
		model.addAttribute("mobiles",MobileList);
		return "display";
	}
	
	@GetMapping("insert")
	public String insertGet() {
		return "insert";
	}
	
	@PostMapping("insert")
	public String insertPost(Mobile mobile) {
		mobileRep.save(mobile);
		return "redirect:/display";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id")Integer id) {
		mobileRep.deleteById(id);
		return "redirect:/display";
	}
	
	@GetMapping("edit/{id}")
	public String editGet(@PathVariable("id")Integer id,Model model) {
		Mobile mobile = mobileRep.findById(id).get();
		model.addAttribute("mobile",mobile);
		return "edit";
	}
	
	@PostMapping("edit")
	public String editPost(Mobile mobile) {
		Integer id = mobile.getId();
		String brand = mobile.getBrand();
		String name = mobile.getName();
		Integer price = mobile.getPrice();
		
		Mobile mobileDB = mobileRep.findById(id).get();
		mobileDB.setBrand(brand);
		mobileDB.setName(name);
		mobileDB.setPrice(price);
		mobileRep.save(mobileDB);
		return "redirect:/display";
	}
}
