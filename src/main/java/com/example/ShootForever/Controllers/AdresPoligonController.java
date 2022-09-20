package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.AdresPoligon;
import com.example.ShootForever.Repo.AdresPoligonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdresPoligonController
{
    @Autowired
    private AdresPoligonRepository adresPoligonRepository;

    @GetMapping("/poligons/spisok")
    public String Poligons(Model model)
    {
        Iterable<AdresPoligon> adresPoligons = adresPoligonRepository.findAll();
        model.addAttribute("adresPoligons",adresPoligons);
        return "poligons/adresPoligon-Spisok";
    }

    @GetMapping("/poligons/add")
    public String PoligonsAdd(AdresPoligon adresPoligon, Model model)
    {
        return "poligons/adresPoligon-Add";
    }

    @PostMapping("/poligons/add")
    public String PoligonsAdd(@ModelAttribute("adresPoligon")@Valid AdresPoligon adresPoligon, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "poligons/adresPoligon-Add";
        }
        adresPoligonRepository.save(adresPoligon);
        return "redirect:/poligons/spisok";
    }

    @GetMapping("/poligons/{id}/edit")
    public String PoligonsEdit(@PathVariable("id")long id, Model model)
    {
        AdresPoligon res = adresPoligonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("adresPoligon",res);
        return "/poligons/adresPoligon-Edit";
    }

    @PostMapping("/poligons/{id}/edit")
    public String PoligonsEdit(@PathVariable("id")long id, @ModelAttribute("adresPoligon")@Valid AdresPoligon adresPoligon, BindingResult bindingResult)
    {
        adresPoligon.setId(id);
        if(bindingResult.hasErrors())
        {
            return "/poligons/adresPoligon-Edit";
        }
        adresPoligonRepository.save(adresPoligon);
        return "redirect:/poligons/spisok";
    }
    @PostMapping("/poligons/{id}/remove")
    public String PoligonsDelete(@PathVariable("id")long id, Model model){
        AdresPoligon adresPoligon = adresPoligonRepository.findById(id).orElseThrow();
        adresPoligonRepository.delete(adresPoligon);
        return "redirect:/poligons/spisok";
    }
}
