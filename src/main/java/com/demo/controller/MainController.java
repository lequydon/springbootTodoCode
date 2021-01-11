package com.demo.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String index(HttpSession session, String Sort, Principal principal, Model model) {
//	 		TodoService todoService=new TodoService();
		int sort = 0;
		if (Sort != null) {
			sort = Integer.parseInt(Sort);
		}
		String email = principal.getName();
		List<Todo> listTodo = new ArrayList<Todo>();
		model.addAttribute("listTodo", listTodo);
		if (session.getAttribute("sortIcon") == null) {
			session.setAttribute("sortIcon", "fas fa-sort");
			listTodo = todoService.getListTodo(email,"");
			System.out.print(listTodo);
			model.addAttribute("listTodo", listTodo);
			return "todo/index";
		}
		if (session.getAttribute("sortIcon").toString().equals("fas fa-sort") && sort == 1) {
			session.setAttribute("sortIcon", "fas fa-sort-up");
			listTodo = todoService.getListTodo(email,"asc");
			model.addAttribute("listTodo", listTodo);
			return "todo/index";
		}
		if (session.getAttribute("sortIcon").toString().equals("fas fa-sort-up") && sort == 1) {
			session.setAttribute("sortIcon", "fas fa-sort-down");
			listTodo = todoService.getListTodo(email,"desc");
			model.addAttribute("listTodo", listTodo);
			return "todo/index";
		}
		if (session.getAttribute("sortIcon").toString().equals("fas fa-sort-down") && sort == 1) {
			session.setAttribute("sortIcon", "fas fa-sort");
			listTodo = todoService.getListTodo(email,"");
			model.addAttribute("listTodo", listTodo);
		}
		// model.addAttribute("sortIcon","fas fa-sort");
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
	public String getModify(int id, Model model, Principal principal) {
		try {
			String emailUserTodo = todoService.getTodo(id).getUser().getEmail();
			String email = principal.getName();
			if (id != 0 && (emailUserTodo).equals(email) == true) {
				Todo todo = todoService.getTodo(id);
				model.addAttribute("todo", todo);
				model.addAttribute("checkData", 1);
			} else {
				Todo todo = new Todo();
				todo.setDealine(new Date());
				model.addAttribute("todo", todo);
				model.addAttribute("checkData", 0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Todo todo = new Todo();
			model.addAttribute("todo", todo);
			model.addAttribute("checkData", 0);
		}
		model.addAttribute("listStatus", statusService.getListStatus());
		return "todo/modify";
	}

	@PostMapping(value = "/modify", produces = "application/json;charset=UTF-8")
	public String postModify(HttpServletRequest request, @Valid @ModelAttribute("todo") TodoDTO todoDTO,
			BindingResult bindingResult, Principal principal) {
		todoDTO.setEmail(principal.getName());
		System.out.print(todoDTO.getContent());
		if (todoDTO.getId() == 0) {

			todoService.addTodo(todoDTO);
		} else {
			todoService.updateTodo(todoDTO);
		}
		return "redirect:/";
	}

	@GetMapping(value = "/delete")
	public String delete(String id) {

		todoService.deleteTodo(Integer.parseInt(id));
		return "redirect:/";
	}

	// @requetparam lấy các giá trị trên url của o input
	// @ModelAttributeđề cập đến một thuộc tính của đối tượng Model (M trong MVC;)
	@PostMapping(value = "/delete", produces = "application/json;charset=UTF-8")
	public String delete(@RequestParam(required = false, name = "checkbox") String[] checkbox) {
		try {
			if (checkbox.length == 0) {
				return "redirect:/";
			}
			for (String i : checkbox) {
				todoService.deleteTodo(Integer.parseInt(i));
			}
			return "redirect:/";
		} catch (Exception e) {
			return "redirect:/";
			// TODO: handle exception
		}
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
