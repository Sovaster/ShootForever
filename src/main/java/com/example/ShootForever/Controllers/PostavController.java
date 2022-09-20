package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.GorodPost;
import com.example.ShootForever.Models.Postav;
import com.example.ShootForever.Repo.GorodPostRepository;
import com.example.ShootForever.Repo.PostavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostavController
{
    @Autowired
    private PostavRepository postavRepository;
    @Autowired
    private GorodPostRepository gorodPostRepository;

    @GetMapping("/postav/spisok")
    public String Postav(Model model)
    {
        Iterable<GorodPost> gorodPost = gorodPostRepository.findAll();
        model.addAttribute("gorodPosts",gorodPost);
        Iterable<Postav> postav = postavRepository.findAll();
        model.addAttribute("postav",postav);
        return "postav/postav-Spisok";
    }

    @GetMapping("/postav/add")
    public String PostavAdd(Postav postav, Model model)
    {
        Iterable<GorodPost> gorodPost = gorodPostRepository.findAll();
        model.addAttribute("gorodPosts",gorodPost);
        return "postav/postav-Add";
    }

    @PostMapping("/postav/add")
    public String PostavAdd(@ModelAttribute("postav")@Valid Postav postav, BindingResult bindingResult,@RequestParam Long gorod, Model model)
    {
        Postav postav1 = postavRepository.findByNazvaniePostav(postav.getNazvaniePostav());
        if(postav1 != null)
        {
            model.addAttribute("message", "Такой поставщик уже есть");
            Iterable<GorodPost> gorodPost = gorodPostRepository.findAll();
            model.addAttribute("gorodPosts",gorodPost);
            return "postav/postav-Add";
        }
        if(bindingResult.hasErrors())
        {
            Iterable<GorodPost> gorodPost = gorodPostRepository.findAll();
            model.addAttribute("gorodPosts",gorodPost);
            return "postav/postav-Add";
        }
        GorodPost gorodPost = gorodPostRepository.findById(gorod).orElseThrow();
        postav.setGorodPost(gorodPost);
        postavRepository.save(postav);
        return "redirect:/postav/spisok";
    }

    @PostMapping("/postav/{id}/remove")
    public String PostavDelete(@PathVariable("id")long id, Model model){
        Postav postav = postavRepository.findById(id).orElseThrow();
        postavRepository.delete(postav);
        return "redirect:/postav/spisok";
    }
}
