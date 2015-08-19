package com.jei.spring.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jei.spring.domain.OmrGicho;
import com.jei.spring.domain.OmrGichoes;
import com.jei.spring.domain.SpOmrGichoes;
import com.jei.spring.service.OmrGichoService;

@Controller
public class OmrGichoController {
	
	@Autowired
	private OmrGichoService omrGichoService;
	
	@RequestMapping(value = {"/omrGicho"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "샘플페이지");
        model.addAttribute("currentPage",1);                
        return "omrGicho";
    }
	
	/**
	 * insert 쿼리 이용하는 컨트롤러
	 * @param omrGicho
	 */
	@RequestMapping(method = RequestMethod.POST , value = "/omrGichoSave")
	@ResponseBody
	public void omrGichoSave(OmrGicho omrGicho){
		
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		omrGicho.setOmrDate(format.format(today));
		omrGicho.setWorkDay(new Timestamp(new Date().getTime()));
		
		omrGichoService.omrGichoSave(omrGicho);
	}
	
	
	/**
	 * SP 이용하는 컨트롤러
	 * @param omrGicho
	 */
	@RequestMapping(method = RequestMethod.POST , value = "/spOmrGichoSave")
	@ResponseBody
	public void spOmrGichoSave(OmrGicho omrGicho){
		
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		omrGicho.setOmrDate(format.format(today));
		
		omrGichoService.spOmrGichoSave(omrGicho);
	}
	
	/**
	 * select 쿼리 이용하는 리스트 컨트롤러
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET , value = "/omrGichoList/{currentPage}",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public OmrGichoes omrGichoList(@PathVariable("currentPage") Integer pCurrentPage){
		
		if(pCurrentPage == null){
			pCurrentPage = 1;
		}
		
		int currentPage = pCurrentPage;
		int rowCount = 10;
		int pageBlockSize = 10;
		int totalCnt = omrGichoService.countOmrGichoList();
		int pageTotalNum = (int) Math.ceil((double)totalCnt/(double)rowCount);
		
		OmrGichoes omrGichoes = new OmrGichoes();
		omrGichoes.setCurrentPage(currentPage);
		omrGichoes.setRowCount(rowCount);
		omrGichoes.setPageBlockSize(pageBlockSize);
		omrGichoes.setTotalCnt(totalCnt);
		omrGichoes.setPageTotalNum(pageTotalNum);
		omrGichoes.setOmrGichoes(omrGichoService.selectOmrGichoList(currentPage,rowCount));
		return omrGichoes;
	}
	
	/**
	 * sp 이용하는 리스트 컨트롤러
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET , value = "/spOmrGichoList/{currentPage}",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public SpOmrGichoes spOmrGichoList(@PathVariable("currentPage") Integer pCurrentPage){
		if(pCurrentPage == null){
			pCurrentPage = 1;
		}
		
		int currentPage = pCurrentPage;
		int rowCount = 10;
		int pageBlockSize = 10;
		
		SpOmrGichoes spOmrGichoes = new SpOmrGichoes();
		spOmrGichoes.setCurrentPage(currentPage);
		spOmrGichoes.setRowCount(rowCount);
		spOmrGichoes.setPageBlockSize(pageBlockSize);
		spOmrGichoes.setSpOmrGichoes(omrGichoService.spOmrGichoList(currentPage, rowCount));
		return spOmrGichoes;
	}
	
	
}
