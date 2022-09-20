package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.*;
import com.example.ShootForever.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class WeaponController
{
    @Autowired
    private LicenziaRepository licenziaRepository;
    @Autowired
    private PostavRepository postavRepository;
    @Autowired
    private WeaponRepository weaponRepository;

    @GetMapping("/weapon/spisok")
    public String Weapon(Model model)
    {
        Iterable<Licenzia> licenzias = licenziaRepository.findAll();
        ArrayList<Licenzia> licenziasArrayList = new ArrayList<>();
        for (Licenzia pass:licenzias)
        {
            if(pass.getWeapon() == null)
            {
                licenziasArrayList.add(pass);
            }
        }
        Iterable<Postav> postavs = postavRepository.findAll();
        ArrayList<Postav> postavsArrayList = new ArrayList<>();
        for (Postav pass:postavs)
        {
            if(pass.getId() != null)
            {
                postavsArrayList.add(pass);
            }
        }
        if(licenziasArrayList.size() == 0)
        {
            Iterable<Weapon> weapons = weaponRepository.findAll();
            model.addAttribute("weapons",weapons);
            model.addAttribute("message", "Нет доступных лицензий для добавления оружия");
            return "weapon/weapon-Spisok2";
        }
        if(postavsArrayList.size() == 0)
        {
            Iterable<Weapon> weapons = weaponRepository.findAll();
            model.addAttribute("weapons",weapons);
            model.addAttribute("message", "Нет доступных поставщиков для добавления оружия");
            return "weapon/weapon-Spisok2";
        }
        Iterable<Weapon> weapons = weaponRepository.findAll();
        model.addAttribute("weapons",weapons);
        return "weapon/weapon-Spisok";
    }

    @GetMapping("/weapon/add")
    public String WeaponAdd(Weapon weapon, Model model)
    {
        Iterable<Licenzia> licenzias = licenziaRepository.findAll();
        ArrayList<Licenzia> licenziasArrayList = new ArrayList<>();
        for (Licenzia pass:licenzias)
        {
            if(pass.getWeapon() == null)
            {
                licenziasArrayList.add(pass);
            }
        }
        Iterable<Postav> postavs = postavRepository.findAll();
        ArrayList<Postav> postavsArrayList = new ArrayList<>();
        for (Postav pass:postavs)
        {
            if(pass.getId() != null)
            {
                postavsArrayList.add(pass);
            }
        }
        model.addAttribute("postavs", postavsArrayList);
        model.addAttribute("licenzias", licenziasArrayList);
        return "weapon/weapon-Add";
    }

    @PostMapping("/weapon/add")
    public String WeaponAdd(@ModelAttribute("weapon")@Valid Weapon weapon,
                             BindingResult bindingResult, Model model,
                             @RequestParam Long licenzia, @RequestParam Long postav)
    {
        if(bindingResult.hasErrors())
        {
            Iterable<Licenzia> licenzias = licenziaRepository.findAll();
            ArrayList<Licenzia> licenziasArrayList = new ArrayList<>();
            for (Licenzia pass:licenzias)
            {
                if(pass.getWeapon() == null)
                {
                    licenziasArrayList.add(pass);
                }
            }
            Iterable<Postav> postavs = postavRepository.findAll();
            ArrayList<Postav> postavsArrayList = new ArrayList<>();
            for (Postav pass:postavs)
            {
                if(pass.getId() != null)
                {
                    postavsArrayList.add(pass);
                }
            }
            model.addAttribute("postavs", postavsArrayList);
            model.addAttribute("licenzias", licenziasArrayList);
            return "weapon/weapon-Add";
        }
        Licenzia licenzia1 = licenziaRepository.findById(licenzia).orElseThrow();
        weapon.setLicenzia(licenzia1);
        Postav postav1 = postavRepository.findById(postav).orElseThrow();
        weapon.setPostav(postav1);
        weaponRepository.save(weapon);
        return "redirect:/weapon/spisok";
    }

    @GetMapping("/weapon/{id}")
    public String WeaponDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Weapon> weapon = weaponRepository.findById(id);
        ArrayList<Weapon> res = new ArrayList<>();
        weapon.ifPresent(res::add);
        model.addAttribute("weapon", res);
        if(!weaponRepository.existsById(id))
        {
            return "redirect:/weapon/spisok";
        }
        return "/weapon/weapon-Details";
    }

    @PostMapping("/weapon/{id}/remove")
    public String WeaponDelete(@PathVariable("id")long id, Model model){
        Weapon weapon = weaponRepository.findById(id).orElseThrow();
        weaponRepository.delete(weapon);
        return "redirect:/weapon/spisok";
    }
}
