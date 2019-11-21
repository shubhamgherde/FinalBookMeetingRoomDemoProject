package com.SecurityDemo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SecurityDemo.demo.model.User;
import com.SecurityDemo.demo.model.mail_request;
import com.SecurityDemo.demo.repository.tlRepository;
import com.SecurityDemo.demo.service.MailRequestService;
import com.SecurityDemo.demo.service.UserService;

@Controller
public class UserCRUDController {
	@Autowired
	UserService userService;

	@Autowired
	MailRequestService mailRequestService;

	@Autowired
	tlRepository repo;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEmp(@ModelAttribute("user") User user) {
		userService.SaveEditUser(user);

		if (user.getRoles().toString().equalsIgnoreCase("[TL]")) {

			int id = user.getId();
			String name = user.getName();
			String email = user.getEmail();
			String dept = user.getDepartment();
			
			repo.saveTl(name, dept, id, email);
		}
		return "redirect:/userManagement/1";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditUserForm(@PathVariable(name = "id") int id) {
		ModelAndView mv = new ModelAndView("newUser/edit_user");
		User user = userService.get(id);
		mv.addObject("user", user);
		return mv;
	}

	@RequestMapping(value = "/edit_mailsave", method = RequestMethod.POST)

	public String bookRoomForm(mail_request mail) {

		mailRequestService.savemailreq(mail);
		return "redirect:/userHome";

	}

}
