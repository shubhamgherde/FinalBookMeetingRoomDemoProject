package com.SecurityDemo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SecurityDemo.demo.model.Room;
import com.SecurityDemo.demo.repository.RoomRepository;
import com.SecurityDemo.demo.service.RoomService;

@Controller
public class RoomCRUDController {

	@Autowired
	RoomService roomService;

	@Autowired
	RoomRepository roomRepository;

	@RequestMapping("/edit_room/{id}")
	public ModelAndView showEditUserForm(@PathVariable(name = "id") int id) {
		ModelAndView mv = new ModelAndView("newRoom/edit_room");
		Room room = roomRepository.findById(id);
		mv.addObject("room", room);
		return mv;
	}

	@RequestMapping(value = "/addroom", method = RequestMethod.POST)
	public String saveEmp(@ModelAttribute("room") Room room) {
		roomService.saveRoom(room);
		return "redirect:/roomManagement/1";
	}

	@RequestMapping(value = "/addRoom", method = RequestMethod.GET)
	public ModelAndView addNewRoomForm() {
		ModelAndView modelAndView = new ModelAndView();
		Room room = new Room();
		modelAndView.addObject("room", room);
		modelAndView.setViewName("newRoom/addNewRoomForm");
		return modelAndView;
	}

	@RequestMapping(value = "/saveRoom", method = RequestMethod.POST)
	public String saveRoom(@ModelAttribute("room") Room room) {

		roomService.SaveEditRoom(room);
		return "redirect:/roomManagement/1";
	}

}
