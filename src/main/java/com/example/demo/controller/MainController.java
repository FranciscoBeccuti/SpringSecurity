package com.example.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * When the url /registration is entered, load this method, mapping the REGISTRATION view. There must be a REGISTRATION.html file inside TEMPLATES.
	 * @return The view REGISTRARION
	 */
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	/**
	 * When the CREATE button is pressed on the REGISTRATION page, it comes to this method. The method validates and create a new user if it does not exist. Note the Valid entry, which brings the object created in the HTML when the object arrives, checks if the email exists in the bbdd if it is so it gives an error with a message. If the mail does not exist in the bbdd then it saves the object and redirects the view to the LOGIN screen.
	 * @param user and object with user data from html Registration
	 * @param bindingResult if any error occurs then report via BindingResult
	 * @return A new view with msg
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user","The email is already registered.");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "The registration was successful");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("login");
			
		}
		return modelAndView;
	}
	
	/**
	 * Home Screen. This URL, maps with the LOGIN view.
	 * @return Login view. 
	 */
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	
	/**
	 * Mapping to the HOME page. Entered the valid user, according to his ROLE, goes to one page or another.
	 * @return A view according to the role of the user. 
	 */
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(){
		
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Get user!
		User user = userService.findUserByEmail(auth.getName());
		//Get Role
		Role userRole = user.getRoles().iterator().next();
		modelAndView.addObject("userName", "Welcome user:" + user.getEmail());
		//Check if Role is ADMIN! or standar ROLE.
		if(userRole.getRole().equalsIgnoreCase("ADMIN")) {
			modelAndView.addObject("adminMessage","Content only for users with admin role");
			modelAndView.setViewName("/admin/home");	
		}
		else {
			modelAndView.addObject("adminMessage","Content for users ");
			modelAndView.setViewName("/home");
		}
		
		return modelAndView;
	}
	

}
