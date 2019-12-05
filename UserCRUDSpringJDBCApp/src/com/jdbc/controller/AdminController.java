package com.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jdbc.model.admin.Admin;
import com.jdbc.service.admin.AdminDaoService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	AdminDaoService adminDaoService;

	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String getAdminHome() {
		return "adminHome";
	}

	@RequestMapping(value = "/adminRegister", method = RequestMethod.GET)
	public ModelAndView getAdminRegisterPage() {
		ModelAndView mav = new ModelAndView("adminRegisterPage");
		mav.addObject("status", getStatus());
		mav.addObject("admin", new Admin());
		return mav;
	}

	@RequestMapping(value = "/createAdmin", method = RequestMethod.POST)
	public ModelAndView createAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("adminRegisterPage");
			mav.addObject("status", getStatus());
			return mav;
		}

		boolean flag = adminDaoService.checkIsExist();

		if (flag == true) {
			mav.setViewName("failure");
			mav.addObject("message", "ADMIN ALREADY EXIT");
			return mav;
		} else {
			boolean flag2 = adminDaoService.createAdmin(admin);
			if (flag2 == true) {
				mav.setViewName("success");
				mav.addObject("message", "ADMIN REGISTRATION SUCCESSFUL");
				return mav;
			}
			else
			{
				mav.setViewName("failure");
				mav.addObject("message", "ADMIN CAN NOT REGISTERED SUCCESSFULLY");
				return mav;
			}
		}

	}
	
	@RequestMapping(value="/showAllUser",method=RequestMethod.GET)
	public ModelAndView getAllUsers(HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("userData",adminDaoService.getAllUsers());
		mav.setViewName("AllUserList");
		return mav;
		
	}
	
	@RequestMapping(value="/approveUser",method=RequestMethod.GET)
	public ModelAndView approveUser(@RequestParam("id") int id)
	{
		ModelAndView mav =new ModelAndView();
		boolean flag = adminDaoService.approveUser(id);
		if(flag == true)
		{
			mav.addObject("userData",adminDaoService.getAllUsers());
			mav.setViewName("AllUserList");
			return mav;
		}
		else {
			mav.setViewName("failure");
			mav.addObject("message", "CAN NOT APPROVED");
		return mav;
		}
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam("id") int id)
	{
		ModelAndView mav =new ModelAndView();
		boolean flag = adminDaoService.deleteUser(id);
		if(flag == true)
		{
			mav.setViewName("AllUserList");
			mav.addObject("userData",adminDaoService.getAllUsers());
			return mav;
		}
		else
		{
			mav.setViewName("failure");
			mav.addObject("message", "USER IS NOT DELETED");
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
