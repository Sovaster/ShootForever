package com.example.ShootForever.Controllers;

import com.example.ShootForever.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController
{
    @GetMapping("/")
    public String Home(Model model)
    {
        return "home";
    }
}
