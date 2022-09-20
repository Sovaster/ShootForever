package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.Role;
import com.example.ShootForever.Models.User;
import com.example.ShootForever.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(User user, Model model){
        return "registration";
    }
    @GetMapping("/registrationAdmin")
    public String registrationAdmin(User user, Model model){
        return "registrationAdmin";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user")@Valid User user, BindingResult bindingResult,Model model){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null)
        {
            model.addAttribute("message", "Сотрудник с таким логином уже зарегистрирован");
            return "registration";
        }
        if(bindingResult.hasErrors())
        {
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
    @PostMapping("/registrationAdmin")
    public String addAdmin(@ModelAttribute("user")@Valid User user, BindingResult bindingResult,Model model){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null)
        {
            model.addAttribute("message", "Админ с таким логином уже зарегистрирован");
            return "registrationAdmin";
        }
        if(bindingResult.hasErrors())
        {
            return "registrationAdmin";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepository.save(user);
        return "redirect:/admin/home";
    }

}
