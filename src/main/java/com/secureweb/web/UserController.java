package com.secureweb.web;

import javax.servlet.http.HttpSession;

import com.secureweb.model.User;
import com.secureweb.service.FileService;
import com.secureweb.service.SecurityService;
import com.secureweb.service.UserService;
import com.secureweb.validator.UserValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	HttpSession session;
	
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private UserDetailsService userdtl;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
    	fileService.getAllFiles();
    	LOGGER.info("sl4j logger--1");
        return "welcome";
    }
    
   
   
}
