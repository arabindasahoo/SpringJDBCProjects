package com.jdbc.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jdbc.model.ForgotPassword;
import com.jdbc.model.Password;
import com.jdbc.model.admin.AdminLogin;
import com.jdbc.service.admin.AdminDaoService;

@Controller
@RequestMapping(value="/adminLogin")
public class AdminLoginController {
	
	@Autowired
	AdminDaoService adminDaoService;
	
	@RequestMapping(value="/adminLoginPage",method=RequestMethod.GET)
	public ModelAndView getAdminLoginPage()
	{
		ModelAndView mav = new ModelAndView("adminLoginPage");
		mav.addObject("loginAdmin",new AdminLogin());
		return mav;
	}
	
	@RequestMapping(value="/loginAsAdmin",method=RequestMethod.POST)
	public ModelAndView getAdminLogin(@Valid@ModelAttribute("loginAdmin") AdminLogin login,BindingResult result,HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors())
		{
			mav.setViewName("adminLoginPage");
			return mav;
		}
		String email = login.getEmail();
		String password = login.getPassword();
	
		boolean flag = adminDaoService.checkAdmin(email, password);
		if(flag == true)
		{
			mav.setViewName("adminPage");
			session.setAttribute("email", email);
			mav.addObject("message", "WELCOME TO YOUR ACCOUNT");
			return mav;
		}
		else
		{
			mav.setViewName("failure");
			mav.addObject("message", "INVALID EMAIL AND PASSWORD");
			return mav;
		}
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logoutAdmin(HttpSession session)
	{
	
		session.getAttribute("email");
		session.invalidate();
		/*
		 * mav.setViewName("adminLoginPage"); return mav;
		 */
		return "redirect:/adminLogin/adminLoginPage";
	}
	
	@RequestMapping(value="/forgotPassword",method=RequestMethod.GET)
	public ModelAndView getForgotPage()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminForgotPasswordPage");
		mav.addObject("ForgotPassword", new ForgotPassword());
		return mav;
	}
	
	@RequestMapping(value="/newPassword",method=RequestMethod.POST)
	public ModelAndView getPassword(@Valid@ModelAttribute("ForgotPassword")ForgotPassword pwd,BindingResult result,HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors())
		{
			mav.setViewName("adminForgotPasswordPage");
			return mav;
		}
		
		String email = pwd.getEmail();
		String phone = pwd.getPhone();
		
		boolean flag = adminDaoService.updatePasswordCheck(email, phone);
		if(flag == true)
		{
			mav.setViewName("adminNewPassword");
			mav.addObject("Password", new Password());
			session.setAttribute("email", email);
			return mav;
		}
		else
		{
			mav.setViewName("failure");
			mav.addObject("message", "INCORRECT CREDENTIALS ENTERED");
			return mav;
		}
	}
	
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public ModelAndView changePassword(@Valid@ModelAttribute("Password")Password pw,BindingResult result,HttpSession session)
	{
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors())
		{
			mav.setViewName("adminNewPassword");
			return mav;
		}
		
		String email = (String)session.getAttribute("email");
		String password = pw.getPassword();
		if(adminDaoService.updatePassword(password, email))
		{
			mav.setViewName("success");
			mav.addObject("message","ADMIN PASSWORD CHANGED SUCCESSFULLY");
			return mav;
		}
		else
		{
			mav.setViewName("failure");
			mav.addObject("message", "FAILED TO CHANGE PASSWORD");
			return mav;
		}
		
	}
	
}
