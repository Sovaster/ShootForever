package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.Klienti;
import com.example.ShootForever.Models.Suvenirs;
import com.example.ShootForever.Repo.KlientRepository;
import com.example.ShootForever.Repo.SuvenirsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SuvenirsController
{
    @Autowired
    private SuvenirsRepository suvenirsRepository;

    @GetMapping("/suvenirs/spisok")
    public String Suvenirs(Model model)
    {
        Iterable<Suvenirs> suvenirs = suvenirsRepository.findAll();
        model.addAttribute("suvenirs",suvenirs);
        return "suvenirs/suvenirs-Spisok";
    }

    @GetMapping("/suvenirs/add")
    public String SuvenirsAdd(Suvenirs suvenirs, Model model)
    {
        return "suvenirs/suvenirs-Add";
    }

    @PostMapping("/suvenirs/add")
    public String SuvenirsAdd(@ModelAttribute("suvenirs")@Valid Suvenirs suvenirs, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "suvenirs/suvenirs-Add";
        }
        suvenirsRepository.save(suvenirs);
        return "redirect:/suvenirs/spisok";
    }

    @GetMapping("/suvenirs/{id}")
    public String SuvenirsDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Suvenirs> suvenirs = suvenirsRepository.findById(id);
        ArrayList<Suvenirs> res = new ArrayList<>();
        suvenirs.ifPresent(res::add);
        model.addAttribute("suvenirs", res);
        if(!suvenirsRepository.existsById(id))
        {
            return "redirect:/suvenirs/spisok";
        }
        return "/suvenirs/suvenirs-Details";
    }

    @GetMapping("/suvenirs/{id}/edit")
    public String SuvenirsEdit(@PathVariable("id")long id, Model model)
    {
        Suvenirs res = suvenirsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("suvenirs",res);
        return "/suvenirs/suvenirs-Edit";
    }

    @PostMapping("/suvenirs/{id}/edit")
    public String SuvenirsEdit(@PathVariable("id")long id, @ModelAttribute("suvenirs")@Valid Suvenirs suvenirs, BindingResult bindingResult)
    {
        suvenirs.setId(id);
        if(bindingResult.hasErrors())
        {
            return "/suvenirs/suvenirs-Edit";
        }
        suvenirsRepository.save(suvenirs);
        return "redirect:/suvenirs/spisok";
    }
    @PostMapping("/suvenirs/{id}/remove")
    public String SuvenirsDelete(@PathVariable("id")long id, Model model){
        Suvenirs suvenirs = suvenirsRepository.findById(id).orElseThrow();
        suvenirsRepository.delete(suvenirs);
        return "redirect:/suvenirs/spisok";
    }
}
