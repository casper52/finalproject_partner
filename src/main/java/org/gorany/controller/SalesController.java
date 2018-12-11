package org.gorany.controller;

import java.util.List;
import java.util.Locale;

import org.gorany.domain.mapper.SalesVO;
import org.gorany.service.SalesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class SalesController {

	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);

	@Setter(onMethod_=@Autowired)
	private SalesService service;
	
	@RequestMapping(value = "sales", method = RequestMethod.GET)
	public String Sales(Locale locale, Model model) {
		return "Sales";
	}
	
	@RequestMapping(value = "salesList", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String incomeList(Locale locale, Model model) {
		Gson gson = new Gson();
		List<SalesVO> list = service.getDailySales();
		
		return gson.toJson(list);
	}
}
