package com.SecurityDemo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.SecurityDemo.demo.model.User;

@SpringBootApplication
@RestController
public class SpringLoginApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
	
		SpringApplication.run(SpringLoginApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringLoginApplication.class);
	}

	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public ModelAndView displayLogin(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
}
