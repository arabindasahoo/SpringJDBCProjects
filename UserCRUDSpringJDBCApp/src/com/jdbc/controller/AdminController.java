package com.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jdbc.model.admin.Admin;
import com.jdbc.model.admin.AdminLogin;
import com.jdbc.service.admin.AdminDaoService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	private static Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	AdminDaoService adminDaoService;

	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String getAdminHome() {
		logger.info("ADMIN HOME PAGE RENDER TO BROWSER");
		return "adminHome";
	}

	@RequestMapping(value = "/adminRegister", method = RequestMethod.GET)
	public ModelAndView getAdminRegisterPage() {
		ModelAndView mav = new ModelAndView("adminRegisterPage");
		mav.addObject("status", getStatus());
		mav.addObject("admin", new Admin());
		logger.info("ADMIN REGISTER PAGE RENDER TO BROWSER");
		return mav;
	}

	@RequestMapping(value = "/createAdmin", method = RequestMethod.POST)
	public ModelAndView createAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			logger.info("ADMIN REGISTER FORM PAGE HAS ERROR");
			mav.setViewName("adminRegisterPage");
			mav.addObject("status", getStatus());
			logger.info("ADMIN REGISTER PAGE RENDER TO BROWSER AGAIN");
			return mav;
		}

		boolean flag = adminDaoService.checkIsExist();

		if (flag == true) {
			logger.info("BEFORE SAVING INTO DATA BASE ADMIN EXISTENCE CHECKED");
			mav.setViewName("failure");
			mav.addObject("message", "ADMIN ALREADY EXIT");
			logger.info("ADMIN ALREADY EXIST");
			return mav;
		} else {
			logger.info("CREAT ADMIN METHOD CALLED");
			boolean flag2 = adminDaoService.createAdmin(admin);
			if (flag2 == true) {
				mav.setViewName("success");
				mav.addObject("message", "ADMIN REGISTRATION SUCCESSFUL");
				logger.info("ADMIN SUCCESSFULLY REGISTER");
				return mav;
			}
			else
			{
				mav.setViewName("failure");
				mav.addObject("message", "ADMIN CAN NOT REGISTERED SUCCESSFULLY");
				logger.info("ADMIN IS NOT SUCCESSFULLY REGISTERED");
				return mav;
			}
		}

	}
	
	@RequestMapping(value="/showAllUser",method=RequestMethod.GET)
	public ModelAndView getAllUsers(HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		String email = (String)session.getAttribute("email");
		String password = (String)session.getAttribute("password");
		
		if(email !=null && password != null)
		{
		logger.info("ADMIN CALLED SHOW USE LIST");
		mav.addObject("userData",adminDaoService.getAllUsers());
		mav.setViewName("AllUserList");
		logger.info("ALL USER LIST PAGE RENDER TO BROWSER");
		return mav;
		}
		else
		{
			mav.setViewName("adminLoginPage");
			mav.addObject("loginAdmin",new AdminLogin());
			return mav;
		}
		
	}
	
	@RequestMapping(value="/approveUser",method=RequestMethod.GET)
	public ModelAndView approveUser(@RequestParam("id") int id)
	{
		ModelAndView mav =new ModelAndView();
		logger.info("ADMIN CALLED APPROVE USER METHOD");
		boolean flag = adminDaoService.approveUser(id);
		if(flag == true)
		{
			mav.addObject("userData",adminDaoService.getAllUsers());
			mav.setViewName("AllUserList");
			logger.info("USER SUCESSFULLY APPROVED BY ADMIN");
			return mav;
		}
		else {
			mav.setViewName("failure");
			mav.addObject("message", "CAN NOT APPROVED");
			logger.info("USER CAN NOT BE APPROVED BY ADMIN");
		return mav;
		}
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam("id") int id)
	{
		ModelAndView mav =new ModelAndView();
		logger.info("ADMIN CALLED DELETE USER METHOD");
		boolean flag = adminDaoService.deleteUser(id);
		if(flag == true)
		{
			mav.setViewName("AllUserList");
			mav.addObject("userData",adminDaoService.getAllUsers());
			logger.info("USER SUCCESSFULLY DELETED");
			return mav;
		}
		else
		{
			mav.setViewName("failure");
			mav.addObject("message", "USER IS NOT DELETED");
			logger.info("USER CAN NOT BE DELETED");
			return mav;
		}
	}

	public List<String> getStatus() {
		List<String> status = new ArrayList<String>();
		status.add("MARRIED");
		status.add("UNMARRIED");
		status.add("DIVORCED");
		return status;
	}

}
