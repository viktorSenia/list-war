package com.leo.test.list.war.controller;

import com.leo.test.list.war.model.User;
import com.leo.test.list.war.repository.UserRepository;
import com.leo.test.list.war.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SecurityController {
    @Autowired
    UserRepository repository;

    @Autowired
    UserValidator validator;

    @Autowired
    BCryptPasswordEncoder encoder;

    @RequestMapping(path = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", true);
        if (logout != null)
            model.addAttribute("logout", true);
        return "security/login";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "security/registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid User user, BindingResult result) {
        validator.validate(user, result);
        if (result.hasErrors())
            return "security/registration";
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "redirect:/login";
    }
}
