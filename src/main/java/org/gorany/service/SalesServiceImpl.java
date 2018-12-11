package org.gorany.service;

import java.util.List;

import org.gorany.domain.mapper.SalesVO;
import org.gorany.mapper.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class SalesServiceImpl implements SalesService {

	@Setter(onMethod_=@Autowired)
	private SalesMapper mapper;
	
	@Override
	public List<SalesVO> getDailySales() {
		
		return mapper.getDailySales();
	}

	
}
