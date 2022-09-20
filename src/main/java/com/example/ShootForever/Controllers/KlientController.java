package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.Klienti;
import com.example.ShootForever.Repo.KlientRepository;
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
public class KlientController
{
    @Autowired
    private KlientRepository klientRepository;

    @GetMapping("/klients/spisok")
    public String Klients(Model model)
    {
        Iterable<Klienti> klientis = klientRepository.findAll();
        model.addAttribute("klienti",klientis);
        return "klients/klients-Spisok";
    }

    @GetMapping("/klients/add")
    public String KlientsAdd(Klienti klienti, Model model)
    {
        LocalDate date = LocalDate.now();
        date = date.minusYears(18);
        date = date.minusDays(1);
        model.addAttribute("date",date);
        return "klients/klients-Add";
    }

    @PostMapping("/klients/add")
    public String KlientsAdd(@ModelAttribute("klienti")@Valid Klienti klienti, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "klients/klients-Add";
        }
        klientRepository.save(klienti);
        return "redirect:/klients/spisok";
    }

    @GetMapping("/klients/filter")
    public String KlientsFilter(Model model)
    {
        return "klients/klients-Filter";
    }

    @PostMapping("/klients/filter/result")
    public String KlientsFilterResult(@RequestParam String familiaKlienta, Model model)
    {
        List<Klienti> result = klientRepository.findByFamiliaKlientaContains(familiaKlienta);
        model.addAttribute("result", result);
        return "klients/klients-Filter";
    }

    @GetMapping("/klients/{id}")
    public String KlientsDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Klienti> klienti = klientRepository.findById(id);
        ArrayList<Klienti> res = new ArrayList<>();
        klienti.ifPresent(res::add);
        model.addAttribute("klienti", res);
        if(!klientRepository.existsById(id))
        {
            return "redirect:/klients/spisok";
        }
        return "/klients/klients-Details";
    }

    @GetMapping("/klients/{id}/edit")
    public String KlientsEdit(@PathVariable("id")long id, Model model)
    {
        LocalDate date = LocalDate.now();
        date = date.minusYears(18);
        date = date.minusDays(1);
        model.addAttribute("date",date);
        Klienti res = klientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("klienti",res);
        return "/klients/klients-Edit";
    }

    @PostMapping("/klients/{id}/edit")
    public String KlientsEdit(@PathVariable("id")long id, @ModelAttribute("klienti")@Valid Klienti klienti, BindingResult bindingResult)
    {
        klienti.setId(id);
        if(bindingResult.hasErrors())
        {
            return "/klients/klients-Edit";
        }
        klientRepository.save(klienti);
        return "redirect:/klients/spisok";
    }
    @PostMapping("/klients/{id}/remove")
    public String KlientsDelete(@PathVariable("id")long id, Model model){
        Klienti klienti = klientRepository.findById(id).orElseThrow();
        klientRepository.delete(klienti);
        return "redirect:/klients/spisok";
    }
}
