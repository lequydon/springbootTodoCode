package com.demo.controller;


import java.security.Principal;
import java.util.List;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.Service.TodoService;
import com.demo.Service.Interface.ITodoService;
import com.demo.entity.Todo;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.ui.Model;
@Controller
public class MainController {
	@Autowired
	TodoService todoService;
	 	@GetMapping("/")
	    public String index(Principal principal,Model model) {
//	 		TodoService todoService=new TodoService();
	 		String email=principal.getName();
	 		List<Todo> listTodo = todoService.getListTodo(email);
	 		model.addAttribute("listTodo", listTodo);
	        return "todo/index";
	    }
	    @GetMapping("/403")
	    public String accessDenied() {
	        return "403";
	    }

	    @GetMapping("/login") 
	    public String getLogin() {
	        return "login";
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
