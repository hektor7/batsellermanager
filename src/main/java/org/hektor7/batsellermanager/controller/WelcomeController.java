package org.hektor7.batsellermanager.controller;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/")
	public String welcome(Model model) {
		
		this.logger.debug("Entering into welcome page :) ");
		
		return "welcome";
	}

}