package org.ict.mensainfoservice.controller;

import org.ict.mensainfoservice.entity.CustomUser;
import org.ict.mensainfoservice.service.MensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MensaService mensaService;

    @RequestMapping(method = RequestMethod.GET, path = "/registration")
    public ModelAndView home(ModelAndView modelAndView){
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/registration")
    public ModelAndView registerNewUser(@RequestParam("username") String username,
                                        @RequestParam("email") String email,
                                        @RequestParam("password") String password,
                                        @RequestParam("password_confirm") String confirmPassword){

        System.out.println("New User: ");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("EMail: " + email);

        ModelAndView modelAndView = new ModelAndView();

        if(!password.equals(confirmPassword)) System.out.println("Passwords don't match!");
        else{
            CustomUser createdUser = new CustomUser(username, passwordEncoder.encode(password), email);
            System.out.println("Created new User: " + createdUser);
            modelAndView.addObject("createdUser", mensaService.saveUser(createdUser));
        }
        return home(modelAndView);
    }
}
