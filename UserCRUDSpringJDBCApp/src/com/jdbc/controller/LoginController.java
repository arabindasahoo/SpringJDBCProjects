package com.jdbc.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.jdbc.model.ForgotPassword;
import com.jdbc.model.Password;
import com.jdbc.model.User;
import com.jdbc.model.UserLogin;
import com.jdbc.service.UserServiceDao;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	UserServiceDao userService;

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView("loginPage");
		mav.addObject("userLogin", new UserLogin());
		logger.info("USER LOGIN PAGE RENDER TO THE BROWSER");
		return mav;

	}

	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ModelAndView getLoginUser(@Valid @ModelAttribute("userLogin") UserLogin login, BindingResult result,HttpSession session ) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			logger.error("USER LOGIN PAGE ERROR");
			mav.setViewName("loginPage");
			logger.info("USER LOGIN PAGE RENDER TO THE BROWSER AGAIN");
			return mav;
		}
		String email = login.getEmail();
		String password = login.getPassword();
		List<User> user= userService.getUser(email, password);
		if(user.size() == 0)
		{
			{ mav.setViewName("failure");
			mav.addObject("message", "INVALID CREDENTIALS USER NOT FOUND"); 
			logger.info("USER LOGIN INVALID CREDENTIAL");
			return mav; }
		}
		
		boolean flag = userService.checkUser(email, password);
		if(flag == true)
		{
		
		mav.setViewName("failure");
		mav.addObject("message", "USER IS NOT APPROVED BY ADMIN YET");
		logger.info("USER NOT APPROVED");
		return mav;
		}
		
		else
			{
			mav.setViewName("userHome");
			mav.addObject("userData", userService.getUser(email, password));
			session.setAttribute("email", email);
			logger.info("USER SUCCESSFULLY LOGGED IN");
			return mav;
			}  
		}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public ModelAndView getForgotPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forgotPasswordPage");
		mav.addObject("ForgotPassword", new ForgotPassword());
		logger.info("USER FORGOT PASSWORD PAGE RENDER TO THE BROWSER");

		return mav;
	}

	@RequestMapping(value = "/newPassword", method = RequestMethod.POST)
	public ModelAndView checkEmailExist(@Valid @ModelAttribute("ForgotPassword") ForgotPassword fp,
			BindingResult result,HttpSession session) {
		ModelAndView mav = new ModelAndView();

		if (result.hasErrors()) {
			mav.setViewName("forgotPasswordPage");
			logger.error("USER FORGOT PASSWORD PAGE HAS ERROR");
			return mav;
		}

		String email = fp.getEmail();
		String phone = fp.getPhone();
		boolean flag = userService.checkIsExist(email, phone);
		if (flag == true) {
			mav.setViewName("newPassword");
			mav.addObject("Password", new Password());
			session.setAttribute("email", email);
			logger.info("USER EXIST INSIDE DATABASE CHECKED");
			return mav;
		} else {
			mav.setViewName("failure");
			mav.addObject("message", "USER IS NOT EXIT WITH THIS EMAIL AND PHONE");
			logger.info("USER IS INVALID OR DOES DONT EXIST INSIDE DATABASE");
			return mav;
		}
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@Valid @ModelAttribute("Password") Password p, BindingResult result,HttpSession session) {

		ModelAndView mav = new ModelAndView();

		if (result.hasErrors()) {
			mav.setViewName("newPassword");
			logger.error("USER PASSWORD PAGE HAS ERROR");
			return mav;
		}
		String email = (String)session.getAttribute("email");
		
		String password = p.getPassword();
		boolean flag = userService.updatePassword(password,email);
		if (flag == true) {
			mav.setViewName("success");
			mav.addObject("message", "PASSWORD HAS BEEN CHANGED SUCCESSFULLY");
			logger.info("PASSWORD CHENGED SUCCESSFULLY");
			return mav;
		} else {
			mav.setViewName("failure");
			mav.addObject("message", "PASSWORD NOT CHANGED");
			logger.info("USER PASSWORD CAN NOT BE CHANGED");
			return mav;
		}
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public  ModelAndView getLogout(HttpSession session)
	{
		ModelAndView mav = new ModelAndView("loginPage");
		mav.addObject("userLogin", new UserLogin());
		session.invalidate();
		logger.info("USER SUCCESSFULLY LOGGED OUT");
		return mav;
	}
	
	
}
