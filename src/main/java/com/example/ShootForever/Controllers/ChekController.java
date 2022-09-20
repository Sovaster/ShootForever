package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.Poligons;
import com.example.ShootForever.Models.Seansi;
import com.example.ShootForever.Models.Weapon;
import com.example.ShootForever.Repo.PoligonsRepository;
import com.example.ShootForever.Repo.SeansiRepository;
import com.example.ShootForever.Repo.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ChekController
{
    @Autowired
    private SeansiRepository seansiRepository;
    @Autowired
    private PoligonsRepository poligonsRepository;
    @Autowired
    private WeaponRepository weaponRepository;

    @GetMapping("/seansi/spisok")
    public String Seansi(Model model)
    {
        Iterable<Poligons> poligons = poligonsRepository.findAll();
        ArrayList<Poligons> poligonsArrayList = new ArrayList<>();
        for (Poligons pass:poligons)
        {
            if(pass.getId() != null)
            {
                poligonsArrayList.add(pass);
            }
        }
        Iterable<Weapon> weapon = weaponRepository.findAll();
        ArrayList<Weapon> weaponArrayList = new ArrayList<>();
        for (Weapon pass:weapon)
        {
            if(pass.getId() != null)
            {
                weaponArrayList.add(pass);
            }
        }
        if(poligonsArrayList.size() == 0)
        {
            model.addAttribute("weapon",weapon);
            model.addAttribute("poligons",poligons);
            Iterable<Seansi> seansi = seansiRepository.findAll();
            model.addAttribute("seansi",seansi);
            model.addAttribute("message", "Нет доступных полигонов для добавления сеанса");
            return "seansi/seansi-Spisok2";
        }
        if(weaponArrayList.size() == 0)
        {
            model.addAttribute("weapon",weapon);
            model.addAttribute("poligons",poligons);
            Iterable<Seansi> seansi = seansiRepository.findAll();
            model.addAttribute("seansi",seansi);
            model.addAttribute("message", "Нет доступного оружия для добавления сеанса");
            return "seansi/seansi-Spisok2";
        }
        Iterable<Seansi> seansi = seansiRepository.findAll();
        model.addAttribute("seansi",seansi);
        return "seansi/seansi-Spisok";
    }

    @GetMapping("/seansi/add")
    public String SeansiAdd(Seansi seansi, Model model)
    {
        Iterable<Poligons> poligons = poligonsRepository.findAll();
        Iterable<Weapon> weapon = weaponRepository.findAll();
        model.addAttribute("weapon", weapon);
        model.addAttribute("poligons", poligons);
        return "seansi/seansi-Add";
    }

    @PostMapping("/seansi/add")
    public String SeansiAdd(@ModelAttribute("seansi")@Valid Seansi seansi,
                             BindingResult bindingResult, Model model,
                             @RequestParam Long weapons, @RequestParam Long poligon)
    {
        if(bindingResult.hasErrors())
        {
            Iterable<Poligons> poligons = poligonsRepository.findAll();
            Iterable<Weapon> weapon = weaponRepository.findAll();
            model.addAttribute("weapon", weapon);
            model.addAttribute("poligons", poligons);
//            System.out.println(bindingResult.getAllErrors());------------------------------------------------------------------------------
            return "seansi/seansi-Add";
        }
        System.out.println(bindingResult.getAllErrors());
        Weapon weapon = weaponRepository.findById(weapons).orElseThrow();
        seansi.setWeapon(weapon);
        Poligons poligons = poligonsRepository.findById(poligon).orElseThrow();
        seansi.setPoligons(poligons);
        seansiRepository.save(seansi);
        return "redirect:/seansi/spisok";
    }

    @GetMapping("/seansi/{id}")
    public String SeansiDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Seansi> seansi = seansiRepository.findById(id);
        ArrayList<Seansi> res = new ArrayList<>();
        seansi.ifPresent(res::add);
        model.addAttribute("seansi", res);
        if(!seansiRepository.existsById(id))
        {
            return "redirect:/seansi/spisok";
        }
        return "/seansi/seansi-Details";
    }

    @PostMapping("/seansi/{id}/remove")
    public String SeansiDelete(@PathVariable("id")long id, Model model){
        Seansi seansi = seansiRepository.findById(id).orElseThrow();
        seansiRepository.delete(seansi);
        return "redirect:/seansi/spisok";
    }
}
