package com.SecurityDemo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SecurityDemo.demo.model.Date;
import com.SecurityDemo.demo.model.Room;
import com.SecurityDemo.demo.model.RoomBookingDetails;
import com.SecurityDemo.demo.model.User;
import com.SecurityDemo.demo.model.mail_request;
import com.SecurityDemo.demo.repository.MailRequestRepository;
import com.SecurityDemo.demo.service.EmailSenderService;
import com.SecurityDemo.demo.service.MailRequestService;
import com.SecurityDemo.demo.service.RoomBookingService;
import com.SecurityDemo.demo.service.RoomService;
import com.SecurityDemo.demo.service.UserService;

@Controller
public class PMController {

	String status;

	@Autowired
	MailRequestService mailRequestService;

	@Autowired
	UserService userservice;

	@Autowired
	MailRequestRepository repo;

	@Autowired
	RoomService roomService;

	@Autowired
	RoomBookingService roomBookingService;

	@Autowired
	private EmailSenderService emailSenderService;

	@RequestMapping(value = "/pendingStatusPm", method = RequestMethod.GET)

	public String pendingStatus(Authentication authentication, Model m) {

		String user = authentication.getName();

		List<RoomBookingDetails> list = roomBookingService.pendingStatus(user);
		if (list.isEmpty()) {
			m.addAttribute("message", "Pending Request");
			return "pmPages/error";
		} else {
			m.addAttribute("list", list);
			return "pmPages/pendingRequestPm";
		}

	}

	@RequestMapping(value = "/pmProfile", method = RequestMethod.GET)

	public String userProfile(Authentication authentication, Model m) {

		String USER = authentication.getName();

		User userProfile = userservice.findByEmail(USER);

		m.addAttribute("userProfile", userProfile);

		return "pmPages/profile";

	}

	@RequestMapping(value = "/confirmStatusPm", method = RequestMethod.GET)

	public String confirmStatus(Authentication authentication, Model m) {

		String user = authentication.getName();

		List<RoomBookingDetails> list = roomBookingService.confirmStatus(user);
		if (list.isEmpty()) {
			m.addAttribute("message", "Confirm Request");
			return "pmPages/error";
		} else {
			m.addAttribute("list", list);
			return "pmPages/confirmRequestPm";
		}
	}

	@RequestMapping(value = "/cancelStatusPm", method = RequestMethod.GET)

	public String cancelStatus(Authentication authentication, Model m) {
		String user = authentication.getName();

		List<RoomBookingDetails> list = roomBookingService.cancelStatus(user);
		if (list.isEmpty()) {
			m.addAttribute("message", "Cancel Request");
			return "pmPages/error";
		} else {
			m.addAttribute("list", list);
			return "pmPages/cancelRequestPm";
		}
	}

	@RequestMapping(value = "/viewAvailableRoomForPm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView register1() {
		ModelAndView modelAndView = new ModelAndView();
		Date date = new Date();
		modelAndView.addObject("date", date);
		modelAndView.setViewName("pmPages/viewAvailableRoomForPm"); // resources/template/register.html
		return modelAndView;
	}

	@RequestMapping(value = "/book_room_datePm", method = RequestMethod.POST)

	public String view2(Model m, Date date) {

		String status = "CONFIRM";
		String date1 = date.getDate1();
		String date2 = date.getDate2();

		List<Room> list = roomService.availableRoomByDate(date1, date2, status);
		m.addAttribute("date1", date.getDate1());
		m.addAttribute("date2", date.getDate2());
		m.addAttribute("list", list);

		return "pmPages/viewAvailableRoomForPm";

	}

	@RequestMapping("/bookRoomFormPm/{id}/{date1}/{date2}")
	public ModelAndView bookRoomForm(@PathVariable(name = "id") int id, @PathVariable(name = "date1") String date1,
			@PathVariable(name = "date2") String date2) {

		ModelAndView mv = new ModelAndView("newBooking/bookRoomFormPm");
		Room room = roomService.get(id);
		mv.addObject("room", room);
		mv.addObject("date1", date1);
		mv.addObject("date2", date2);

		return mv;
	}

	@RequestMapping(value = "/bookRoomPm", method = RequestMethod.POST)
	public String bookRoomTl(RoomBookingDetails room) {

		int id = room.getId();

		String status = "PENDING";

		roomBookingService.saveBookingRoom(room);
		roomService.updateStatusPending(id, status);
		return "redirect:/pmHome";
	}

	@RequestMapping(value = "/Change_emailPm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView changeemail() {
		ModelAndView modelAndView = new ModelAndView();

		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userservice.findByM(auth.getName());

		String role1 = user.getRoles().toString();
		String dept = user.getDepartment();

		modelAndView.addObject("role", role1);
		modelAndView.addObject("dept", dept);

		modelAndView.setViewName("pmPages/editEmailForm"); // resources/template/admin.html
		return modelAndView;
	}

	@RequestMapping(value = "/edit_mailPm", method = RequestMethod.POST)

	public String bookRoomForm(mail_request mail) {
		mailRequestService.savemailreq(mail);

		return "redirect:/pmHome";

	}

	@RequestMapping("/pendingMailReqPm")
	public String pendingmailrequset1(Model m) {

		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userservice.findByM(auth.getName());

		status = "PENDING";
		List<mail_request> mail = mailRequestService.listpendingTL(status);
		
		if(mail.isEmpty())
		{
			m.addAttribute("message", "Pending Mail Request");
			return "pmPages/errorForMailReq";
			
		}
		else
		{
			m.addAttribute("mail", mail);
			return "pmPages/pendingMailrequesPm";
		}
	}

	@RequestMapping("/confirmMailReqPm")
	public String confirmmailrequset1(Model m) {

		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userservice.findByM(auth.getName());

		status = "CONFIRM";
		List<mail_request> mail = mailRequestService.listpendingTL(status);
		

		if(mail.isEmpty())
		{
			m.addAttribute("message", "Confirm Mail Request");
			return "pmPages/errorForMailReq";
			
		}
		else
		{
			m.addAttribute("mail", mail);
			m.addAttribute("message", "Confirm Mail Request");
			return "pmPages/confirmAndCancelMailrequesPm";
		}
	}

	@RequestMapping("/cancelMailReqPm")
	public String cancelmailrequset1(Model m) {

		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		User user = userservice.findByM(auth.getName());

		status = "CANCEL";
		List<mail_request> mail = mailRequestService.listpendingTL(status);
		if(mail.isEmpty())
		{
			m.addAttribute("message", "Cancel Mail Request");
			return "pmPages/errorForMailReq";
			
		}
		else
		{
			m.addAttribute("mail", mail);
			m.addAttribute("message", "Cancel Mail Request");
		
		return "pmPages/confirmAndCancelMailrequesPm";
		}
	}

	@RequestMapping(value = "/changeConfirmmailPm/{id}")
	public String confirmRoom(@PathVariable(name = "id") int id) {

		String status = "CONFIRM";
		mailRequestService.updateStatusmail(id, status);

		mail_request mail = mailRequestService.get(id);
		String email = mail.getUser_mail();
		String nemail = mail.getNew_user_mail();

		userservice.updateemail(email, nemail);
		roomBookingService.updateemail(email, nemail);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mail.getUser_mail());
		mailMessage.setSubject("Complete Password Reset!");
		mailMessage.setFrom("nairobley@gmail.com");
		mailMessage.setText("Mail has been changed successfully");

		emailSenderService.sendEmail(mailMessage);
		return "redirect:/pendingMailReqPm";

	}

	@RequestMapping(value = "/changeCancelmmailPm/{id}")
	public String cancelmRoom(@PathVariable(name = "id") int id) {

		String status = "CANCEL";

		mailRequestService.updateStatusmail(id, status);

		return "redirect:/pendingMailReqPm";

	}

}