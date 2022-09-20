package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.AdresPoligon;
import com.example.ShootForever.Models.Poligons;
import com.example.ShootForever.Models.User;
import com.example.ShootForever.Repo.AdresPoligonRepository;
import com.example.ShootForever.Repo.PoligonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class PoligonsController
{
    @Autowired
    private AdresPoligonRepository adresPoligonRepository;
    @Autowired
    private PoligonsRepository poligonsRepository;

    @GetMapping("/poligon/spisok")
    public String Poligon(Model model)
    {
        Iterable<AdresPoligon> adresPoligons = adresPoligonRepository.findAll();
        ArrayList<AdresPoligon> adressArrayList = new ArrayList<>();
        for (AdresPoligon pass:adresPoligons)
        {
            if(pass.getPoligons() == null)
            {
                adressArrayList.add(pass);
            }
        }
        if(adressArrayList.size() == 0)
        {
            Iterable<Poligons> poligons = poligonsRepository.findAll();
            model.addAttribute("poligons",poligons);
            model.addAttribute("message", "Нет доступных адресов для добавления полигона");
            return "poligons/poligons-Spisok2";
        }
        Iterable<Poligons> poligons = poligonsRepository.findAll();
        model.addAttribute("poligons",poligons);
        return "poligons/poligons-Spisok";
    }

    @GetMapping("/poligon/add")
    public String PoligonAdd(Poligons poligons, Model model)
    {
        Iterable<AdresPoligon> adresPoligons = adresPoligonRepository.findAll();
        ArrayList<AdresPoligon> adressArrayList = new ArrayList<>();
        for (AdresPoligon pass:adresPoligons)
        {
            if(pass.getPoligons() == null)
            {
                adressArrayList.add(pass);
            }
        }
        model.addAttribute("adresPoligons", adressArrayList);
        return "poligons/poligons-Add";
    }

    @PostMapping("/poligon/add")
    public String PoligonAdd(@ModelAttribute("poligons")@Valid Poligons poligons,
                             BindingResult bindingResult, Model model,
                             @RequestParam Long adresPoligon)
    {
        if(bindingResult.hasErrors())
        {
            Iterable<AdresPoligon> adresPoligons = adresPoligonRepository.findAll();
            ArrayList<AdresPoligon> adressArrayList = new ArrayList<>();
            for (AdresPoligon pass:adresPoligons)
            {
                if(pass.getPoligons() == null)
                {
                    adressArrayList.add(pass);
                }
            }
            model.addAttribute("adresPoligons", adressArrayList);
            return "poligons/poligons-Add";
        }
        AdresPoligon adresPoligon1 = adresPoligonRepository.findById(adresPoligon).orElseThrow();
        poligons.setAdresPoligon(adresPoligon1);
        poligonsRepository.save(poligons);
        return "redirect:/poligon/spisok";
    }

    @PostMapping("/poligon/{id}/remove")
    public String PoligonDelete(@PathVariable("id")long id, Model model){
        Poligons poligons = poligonsRepository.findById(id).orElseThrow();
        poligonsRepository.delete(poligons);
        return "redirect:/poligon/spisok";
    }
}
