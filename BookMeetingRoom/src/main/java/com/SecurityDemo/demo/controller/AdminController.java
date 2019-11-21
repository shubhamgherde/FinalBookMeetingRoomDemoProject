package com.SecurityDemo.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.SecurityDemo.demo.model.Room;
import com.SecurityDemo.demo.model.RoomBookingDetails;
import com.SecurityDemo.demo.model.User;
import com.SecurityDemo.demo.model.mail_request;
import com.SecurityDemo.demo.repository.RoomBookingDetailRepo;
import com.SecurityDemo.demo.repository.RoomRepository;
import com.SecurityDemo.demo.repository.UserRepository;
import com.SecurityDemo.demo.service.EmailSenderService;
import com.SecurityDemo.demo.service.MailRequestService;
import com.SecurityDemo.demo.service.RoomBookingService;
import com.SecurityDemo.demo.service.UserService;

@Controller
public class AdminController {

	String status;

	@Autowired
	UserService userService;

	@Autowired
	RoomBookingDetailRepo roomBookingDetailRepo;

	@Autowired
	UserRepository repo;

	@Autowired
	RoomBookingService roomBookingService;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	MailRequestService mailRequestService;

	@Autowired
	private EmailSenderService emailSenderService;

	@RequestMapping(value = "/userManagement/{page}", method = RequestMethod.GET)
	public ModelAndView userManagement(@PathVariable(value = "page") int page,
			@RequestParam(defaultValue = "id") String sortBy) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail(auth.getName());

		PageRequest pageable = PageRequest.of(page - 1, 5, Sort.Direction.DESC, sortBy);
		Page<User> userPage = repo.findAll(pageable);
		int totalPages = userPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("activeUserList", true);
		modelAndView.addObject("list", userPage.getContent());

		modelAndView.addObject("username", "Welcome " + user.getName() + ")");
		modelAndView.setViewName("newUser/viewuser");
		return modelAndView;
	}

	@RequestMapping(value = "/roomManagement/{page}", method = RequestMethod.GET)
	public ModelAndView roomManagement(@PathVariable(value = "page") int page,
			@RequestParam(defaultValue = "id") String sortBy) {
		ModelAndView modelAndView = new ModelAndView();

		PageRequest pageable = PageRequest.of(page - 1, 5, Sort.Direction.DESC, sortBy);
		Page<Room> userPage = roomRepository.findAll(pageable);
		int totalPages = userPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("activeUserList", true);
		modelAndView.addObject("list", userPage.getContent());

		modelAndView.setViewName("newRoom/viewroom");
		return modelAndView;
	}

	@RequestMapping("/confirmRequest")
	public String confirmRequest(Model m) {
		List<RoomBookingDetails> listRoom = roomBookingService.allConfirmRequest();
		m.addAttribute("listRoom", listRoom);
		return "newBooking/confirmRequest";
	}

	@RequestMapping("/pendingRequest")
	public String bookingRequest(Model m) {
		List<RoomBookingDetails> listRoom = roomBookingService.allPendingRequest();
		m.addAttribute("listRoom", listRoom);
		return "newBooking/pendingRequest";
	}

	@RequestMapping("/cancelRequest")
	public String cancelRequest(Model m) {
		List<RoomBookingDetails> listRoom = roomBookingService.allCancelRequest();
		m.addAttribute("listRoom", listRoom);
		return "newBooking/cancelRequest";
	}

	@RequestMapping("/pendingMailRequest")
	public String mailrequset(Model m) {
		status = "PENDING";
		List<mail_request> mail = mailRequestService.listpendingPM(status);
		if(mail.isEmpty())
		{
			m.addAttribute("message", "Pending Mail Request");
			return "adminPages/errorForMailReq";
			
		}
		else
		{
			m.addAttribute("mail", mail);
			m.addAttribute("message", "Pending Mail Request");
			return "adminPages/pendingMailrequest";
		}
		
	}

	@RequestMapping("/confirmMailRequest")
	public String confirmmailrequset(Model m) {
		status = "CONFIRM";
		List<mail_request> mail = mailRequestService.listpendingPM(status);
		if(mail.isEmpty())
		{
			m.addAttribute("message", "Confirm Mail Request");
			return "adminPages/errorForMailReq";
			
		}
		else
		{
			m.addAttribute("mail", mail);
			m.addAttribute("message", "Confirm Mail Request");
			return "adminPages/confirmAndCancelMailrequest";
		}
	}

	@RequestMapping("/cancelMailRequest")
	public String cancelmailrequset(Model m) {
		status = "CANCEL";
		List<mail_request> mail = mailRequestService.listpendingPM(status);
		if(mail.isEmpty())
		{
			m.addAttribute("message", "Cancel Mail Request");
			return "adminPages/errorForMailReq";
			
		}
		else
		{
			m.addAttribute("mail", mail);
		m.addAttribute("message", "Cancel Mail Request");
		return "adminPages/confirmAndCancelMailrequest";
		}
	}

	@RequestMapping(value = "/changeConfirmmailAdmin/{id}")
	public String confirmRoom(@PathVariable(name = "id") int id) {

		String status = "CONFIRM";
		mailRequestService.updateStatusmail(id, status);

		mail_request mail = mailRequestService.get(id);
		String email = mail.getUser_mail();
		String nemail = mail.getNew_user_mail();

		userService.updateemail(email, nemail);
		roomBookingService.updateemail(email, nemail);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mail.getUser_mail());
		mailMessage.setSubject("Complete Password Reset!");
		mailMessage.setFrom("nairobley@gmail.com");
		mailMessage.setText("Mail has been changed successfully");

		emailSenderService.sendEmail(mailMessage);
		return "redirect:/pendingMailRequest";

	}

	@RequestMapping(value = "/changeCancelmmailAdmin/{id}")
	public String cancelmRoom(@PathVariable(name = "id") int id) {

		String status = "CANCEL";

		mailRequestService.updateStatusmail(id, status);

		return "redirect:/pendingMailRequest";

	}
}
