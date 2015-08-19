package com.jei.spring.repository;

import java.util.List;
import java.util.Map;

import com.jei.spring.domain.OmrGicho;
import com.jei.spring.domain.SpOmrGicho;

public interface OmrGichoRepository {
	
	public void omrGichoSave(OmrGicho omrGicho);

	public void spOmrGichoSave(OmrGicho omrGicho);
	
	public int countOmrGichoList();
		
	public List<OmrGicho> selectOmrGichoList(Map<String,Object> map);
	
	public List<SpOmrGicho> spOmrGichoList(Map<String,Object> map);
}
