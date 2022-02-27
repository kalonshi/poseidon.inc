package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.CheckInputService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CheckInputService checkInputService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    
    @RequestMapping("/user/list")
    public String home(Model model)
    {
    	logger.info("Entering home method for user: List of users");
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
    	logger.info("Entering Add method for new User  ");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
    	logger.info("Entering  validate method to save  new User ");
    	if (!result.hasErrors()&&checkInputService.checkPassword(user.getPassword())) {
        	/*String password=user.getPassword();
        	if(checkInputService.checkPassword(password)) {*/
        		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(user.getPassword()));
                user.setActive(true);
                userRepository.save(user);
                model.addAttribute("users", userRepository.findAll());
                return "redirect:/user/list";
			/* } */
            
        }
    	if(!checkInputService.checkPassword(user.getPassword())) {
    	 model.addAttribute("wrongPassword", true);
    	}
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
       
    	logger.info("Entering  Update user method : Id User to  Update= " + id);
    	   
    	
    	User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
       
    	logger.info("Entering save Update User method : Id User  Updated= " + id);
		
    	
    	if (result.hasErrors()) {
            return "user/update";
        }
    	
    	    	        userRepository.save(user);
    	        model.addAttribute("users", userRepository.findAll());
    	        return "redirect:/user/list";	
    	}
		
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
    	logger.info("Entering Delete method for a User: Id User to delete= " + id);
		
    	User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
}
