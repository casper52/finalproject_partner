package org.gorany.mapper;

import java.util.List;

import org.gorany.domain.mapper.SalesVO;

public interface SalesMapper {

	public List<SalesVO> getDailySales();
}
