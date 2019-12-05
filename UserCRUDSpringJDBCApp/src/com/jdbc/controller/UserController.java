package com.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jdbc.model.User;
import com.jdbc.service.UserServiceDao;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserServiceDao userService;
	
	@RequestMapping(value="/userHome",method=RequestMethod.GET)
	public String getHome()
	{
		return "home";
	}
	
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView getRegisterPage()
	{
	
		ModelAndView mav = new ModelAndView("registerPage");
		mav.addObject("designation", getDesignation());
		mav.addObject("status", getStatus());
		mav.addObject("user", new User());
		logger.info("USER REGISTER PAGE RENDER TO BROWSER");
		return mav;
	}
	
	@RequestMapping(value="/createUser",method=RequestMethod.POST)
	public ModelAndView createUser(@Valid @ModelAttribute("user") User user,BindingResult result)
	{
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors())
		{
			logger.error("USER REGISTER PAGE HAS ERROR");
			mav.setViewName("registerPage");
			mav.addObject("designation", getDesignation());
			mav.addObject("status", getStatus());
			logger.info("USER REGISTER PAGE RENDER TO BROWSER AGAIN");
			return mav;
		}
		String email = user.getEmail();
		String phone = user.getPhone();
		if(userService.checkIsExist(email, phone))
		{
			logger.info("BEFORE REGISTRATION USER FORM DATA CHECKED IN DATA BASE");
			mav.setViewName("failure");
			mav.addObject("message", "USER ALREADY EXIT WITH EMAIL AND PHONE");
			logger.info("USER ALREDY EXIST INSIDE DATA BASE");
			return mav;
		}
		else { 
			boolean flag = userService.createUser(user);
			if(flag == true)
			{
				logger.info("USER REGISTRATION SUCCESSFUll");
				mav.addObject("message", "User Successfully Registered");
				mav.setViewName("success");
				logger.info("SUCCESS PAGE RENDER TO BROWSER");
				return mav;
			}
		else
			{
			mav.setViewName("failure");
			mav.addObject("message", "User Data is not iserted successfully");
			logger.info("FAILURE PAGE RENDER TO BROWSER");
			return mav;
			}
		}
	}
	
	public List<String> getDesignation()
	{
		List<String> designation = new ArrayList<String>();
		designation.add("MANAGER");
		designation.add("TEAM LEAD");
		designation.add("TRAINEE");
		designation.add("CEO");
		
		return designation;
	}
	
	public List<String> getStatus()
	{
		List<String> status = new ArrayList<String>();
		status.add("MARRIED");
		status.add("UNMARRIED");
		status.add("DIVORCED");
		return status;
	}
}
