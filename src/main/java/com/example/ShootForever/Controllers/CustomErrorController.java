package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.GorodPost;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {


    @RequestMapping("/error")
    @GetMapping
    String error(HttpServletRequest request) {
        return "error";
    }
}
