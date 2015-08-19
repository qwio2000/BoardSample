package com.jei.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	
	@RequestMapping(value={"/login","/"})
	public String index(Model model,@RequestParam(value="returl",required=false) String returl){
		 model.addAttribute("title", "로그인페이지");
		 model.addAttribute("requrl",returl);
	     return "login";
	}
	
}
