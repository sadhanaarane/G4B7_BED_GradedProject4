package com.gl.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AccessController {

	@RequestMapping("/403")
	@PreAuthorize("permitAll()")
	public ModelAndView accessDenied(Principal user) {

		ModelAndView model = new ModelAndView();
		if (user != null)
			model.addObject("msg", "Hi " + user.getName() + " you don't have permission to access this page");
		else
			model.addObject("msg", "Hi you don't have permission to access this page");

		model.setViewName("403");
		return model;
	}
}
