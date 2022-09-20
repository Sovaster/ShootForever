package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.AdresPoligon;
import com.example.ShootForever.Models.GorodPost;
import com.example.ShootForever.Models.Suvenirs;
import com.example.ShootForever.Models.User;
import com.example.ShootForever.Repo.GorodPostRepository;
import com.example.ShootForever.Repo.SuvenirsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class GorodPostController
{
    @Autowired
    private GorodPostRepository gorodPostRepository;

    @GetMapping("/gorodPostav/spisok")
    public String GorodPost(Model model)
    {
        Iterable<GorodPost> gorodPosts = gorodPostRepository.findAll();
        model.addAttribute("gorodPosts",gorodPosts);
        return "postav/gorodPost-Spisok";
    }

    @GetMapping("/gorodPostav/add")
    public String GorodPostAdd(GorodPost gorodPost, Model model)
    {
        return "postav/gorodPost-Add";
    }

    @PostMapping("/gorodPostav/add")
    public String GorodPostAdd(@ModelAttribute("gorodPost")@Valid GorodPost gorodPost, BindingResult bindingResult, Model model)
    {
        GorodPost gorodPost1 = gorodPostRepository.findByNazvanieGorodaPost(gorodPost.getNazvanieGorodaPost());
        if(gorodPost1 != null)
        {
            model.addAttribute("message", "Такой город уже есть");
            return "postav/gorodPost-Add";
        }
        if(bindingResult.hasErrors())
        {
            return "postav/gorodPost-Add";
        }
        gorodPostRepository.save(gorodPost);
        return "redirect:/gorodPostav/spisok";
    }

    @PostMapping("/gorodPostav/{id}/remove")
    public String GorodPostDelete(@PathVariable("id")long id, Model model){
        GorodPost gorodPost = gorodPostRepository.findById(id).orElseThrow();
        gorodPostRepository.delete(gorodPost);
        return "redirect:/gorodPostav/spisok";
    }
}
