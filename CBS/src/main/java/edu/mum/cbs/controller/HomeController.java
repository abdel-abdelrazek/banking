package edu.mum.cbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
  
@Controller
public class HomeController {

 
	@RequestMapping({"/","/welcome"})
	public RedirectView welcome(Model model) {
		
		model.addAttribute("greeting", "Welcome to our community, Opza!!");
		model.addAttribute("tagline", "The one and only place to live, work and play!!");
		
		//return "redirect:/customer/index";
		//return "/customer/index";
		return new RedirectView("http://localhost:8080/cbs/customer/index");
	}
 
}
