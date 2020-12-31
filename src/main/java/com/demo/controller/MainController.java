package com.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	 	@GetMapping("/")
	    public String index() {
	        return "index";
	    }

	    @GetMapping("/admin") 
	    public String admin() {
	        return "admin";
	    }

	    @GetMapping("/403")
	    public String accessDenied() {
	        return "403";
	    }

	    @GetMapping("/login") 
	    public String getLogin() {
	        return "login";
	    }
	    @GetMapping("/user") 
	    public String getUser() {
	        return "user";
	    }
//	    @GetMapping("/logout")
//	    public String logout(HttpServletRequest request, HttpServletResponse response) {
//	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	        if (auth != null) {
//	            new SecurityContextLogoutHandler().logout(request, response, auth);
//	        }
//	        return "redirect:/";
//	    }
}
