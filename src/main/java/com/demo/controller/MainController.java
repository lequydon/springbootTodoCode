package com.demo.controller;


import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.Service.StatusService;
import com.demo.Service.TodoService;
import com.demo.Service.UserService;
import com.demo.Service.Interface.ITodoService;
import com.demo.dto.TodoDTO;
import com.demo.dto.TodoFormDTO;
import com.demo.entity.Status;
import com.demo.entity.Todo;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.sun.org.apache.xml.internal.utils.StringComparable;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
@Controller
public class MainController {
	@Autowired
	TodoService todoService;
	@Autowired
	StatusService statusService;
	
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
	    @GetMapping("/modify") 
	    public String getModify(int id,Model model, Principal principal) {
	    	try {
		    	String emailUserTodo =todoService.getTodo(id).getUser().getEmail();
		    	String email =principal.getName();
		    	if(id!=0&&(emailUserTodo).equals(email)==true) {
		    		Todo todo=todoService.getTodo(id);
		    		model.addAttribute("todo",todo);
		    		model.addAttribute("checkData",1);
		    	}
		    	else {
		    		Todo todo= new Todo();
		    		todo.setDealine(new Date());
		    		model.addAttribute("todo",todo);
		    		model.addAttribute("checkData",0);
		    	}
			} catch (Exception e) {
				// TODO: handle exception
	    		Todo todo= new Todo();
	    		model.addAttribute("todo",todo);
	    		model.addAttribute("checkData",0);
			}
	    	model.addAttribute("listStatus",statusService.getListStatus());
	        return "todo/modify";
	    }
	    @PostMapping(value="/modify",produces = "application/json;charset=UTF-8") 
	    public String postModify(HttpServletRequest request, @Valid @ModelAttribute("todo") TodoDTO todoDTO, BindingResult bindingResult,Principal principal) {
	    	todoDTO.setEmail(principal.getName());
	    	System.out.print(todoDTO.getContent()); 
	    	if(todoDTO.getId()==0){

	    		todoService.addTodo(todoDTO);
	    	}
	    	else {
	    		todoService.updateTodo(todoDTO);
	    	}
	    	 return "redirect:/";
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
