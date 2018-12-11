package org.gorany.controller;

import org.gorany.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Controller
public class GeneralController {

	
	private OrderMapper ordermapper;
	
	@GetMapping("/index")
	public void index(Model model) throws Exception {
		log.info("index......................page");
		
		//ordermapper
		model.addAttribute("result", ordermapper.getOrder());
		
	}
	
	
	@GetMapping("/menu")
	public void menu() {
		log.info("menu......................page");
	}
	
	@GetMapping("/superAdmin")
	public void superAdmin() {
		log.info("superAdmin......................page");
	}
	
	@GetMapping("/notice")
	public void notice() {
		log.info("notice......................page");
	}
	
	@GetMapping("/oneByone")
	public void oneByone() {
		log.info("oneByone......................page");
	}
	
	@GetMapping("/information")
	public void information() {
		log.info("information......................page");
	}
	
	
}
