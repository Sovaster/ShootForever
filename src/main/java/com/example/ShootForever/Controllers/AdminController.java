package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.Klienti;
import com.example.ShootForever.Models.Licenzia;
import com.example.ShootForever.Models.Role;
import com.example.ShootForever.Models.User;
import com.example.ShootForever.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController
{
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/home")
    public String Home(Model model)
    {
        return "admin/homeAdmin";
    }

    @GetMapping("/allUsers")
    public String AllUsers(User user, Model model)
    {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "admin/sotr-Details";
    }
}
