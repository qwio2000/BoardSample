package com.jei.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jei.spring.domain.OmrGicho;
import com.jei.spring.domain.SpOmrGicho;
import com.jei.spring.repository.OmrGichoRepository;


@Service
public class OmrGichoService {
	
	@Autowired
	private OmrGichoRepository omrGichoRepository;
	
	/**
	 * insert쿼리 이용해서 하는거
	 */
	@Transactional
	public void omrGichoSave(OmrGicho omrGicho){
		omrGichoRepository.omrGichoSave(omrGicho);
	};
	/**
	 * sp이용해서 하는거
	 * @param omrGicho
	 */
	@Transactional
	public void spOmrGichoSave(OmrGicho omrGicho){
		omrGichoRepository.spOmrGichoSave(omrGicho);
	}
	
	/**
	 * select쿼리 이용
	 * @return
	 */
	public List<OmrGicho> selectOmrGichoList(int currentPage,int rowCount){
		int offSet = (currentPage - 1) * rowCount;
		
		Map<String,Object> map = new HashMap<String,Object>();		
		
		map.put("rowCount",rowCount);
		map.put("offSet",offSet);
		return omrGichoRepository.selectOmrGichoList(map);
	}
	
	public int countOmrGichoList(){
		return omrGichoRepository.countOmrGichoList();
	}
	
	/**
	 * sp이용
	 * @param currentPage
	 * @param rowCount
	 * @return
	 */
	public List<SpOmrGicho> spOmrGichoList(int currentPage,int rowCount){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		map.put("rowCount", rowCount);
		return omrGichoRepository.spOmrGichoList(map);
	}
	
	
}
