package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.*;
import com.example.ShootForever.Repo.*;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ChekController
{
    @Autowired
    private SeansiRepository seansiRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChekRepository chekRepository;
    @Autowired
    private SuvenirsRepository suvenirsRepository;
    @Autowired
    private KlientRepository klientRepository;

    @GetMapping("/chek/spisok")
    public String Chek(Model model)
    {
        Iterable<User> user = userRepository.findAll();
        ArrayList<User> userArrayList = new ArrayList<>();
        for (User pass:user)
        {
            if(pass.getId() != null)
            {
                userArrayList.add(pass);
            }
        }
        Iterable<Seansi> seansi = seansiRepository.findAll();
        ArrayList<Seansi> seansiArrayList = new ArrayList<>();
        for (Seansi pass:seansi)
        {
            if(pass.getChek() == null)
            {
                seansiArrayList.add(pass);
            }
        }
        Iterable<Suvenirs> suvenirs = suvenirsRepository.findAll();
        ArrayList<Suvenirs> suvenirsArrayList = new ArrayList<>();
        for (Suvenirs pass:suvenirs)
        {
            if(pass.getId() != null)
            {
                suvenirsArrayList.add(pass);
            }
        }
        Iterable<Klienti> klienti = klientRepository.findAll();
        ArrayList<Klienti> klientiArrayList = new ArrayList<>();
        for (Klienti pass:klienti)
        {
            if(pass.getId() != null)
            {
                klientiArrayList.add(pass);
            }
        }
        if(userArrayList.size() == 0)
        {
            model.addAttribute("user",user);
            model.addAttribute("seansi",seansi);
            model.addAttribute("suvenirs",suvenirs);
            model.addAttribute("klienti",klienti);
            Iterable<Chek> chek = chekRepository.findAll();
            model.addAttribute("chek",chek);
            model.addAttribute("message", "Нет доступных сотрудников для добавления чека");
            return "chek/chek-Spisok2";
        }
        if(seansiArrayList.size() == 0)
        {
            model.addAttribute("user",user);
            model.addAttribute("seansi",seansi);
            model.addAttribute("suvenirs",suvenirs);
            model.addAttribute("klienti",klienti);
            Iterable<Chek> chek = chekRepository.findAll();
            model.addAttribute("chek",chek);
            model.addAttribute("message", "Нет доступных сеансов для добавления чека");
            return "chek/chek-Spisok2";
        }
        if(suvenirsArrayList.size() == 0)
        {
            model.addAttribute("user",user);
            model.addAttribute("seansi",seansi);
            model.addAttribute("suvenirs",suvenirs);
            model.addAttribute("klienti",klienti);
            Iterable<Chek> chek = chekRepository.findAll();
            model.addAttribute("chek",chek);
            model.addAttribute("message", "Нет доступных сувениров для добавления чека");
            return "chek/chek-Spisok2";
        }
        if(klientiArrayList.size() == 0)
        {
            model.addAttribute("user",user);
            model.addAttribute("seansi",seansi);
            model.addAttribute("suvenirs",suvenirs);
            model.addAttribute("klienti",klienti);
            Iterable<Chek> chek = chekRepository.findAll();
            model.addAttribute("chek",chek);
            model.addAttribute("message", "Нет доступных клиентов для добавления чека");
            return "chek/chek-Spisok2";
        }
        Iterable<Chek> chek = chekRepository.findAll();
        model.addAttribute("chek",chek);
        return "chek/chek-Spisok";
    }

    @GetMapping("/chek/add")
    public String ChekAdd(Chek chek, Model model)
    {
        Iterable<Seansi> seansi = seansiRepository.findAll();
        ArrayList<Seansi> seansiArrayList = new ArrayList<>();
        for (Seansi pass:seansi)
        {
            if(pass.getChek() == null)
            {
                seansiArrayList.add(pass);
            }
        }
        Iterable<User> user = userRepository.findAll();
        Iterable<Suvenirs> suvenirs = suvenirsRepository.findAll();
        Iterable<Klienti> klienti = klientRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("seansi", seansiArrayList);
        model.addAttribute("suvenirs", suvenirs);
        model.addAttribute("klienti", klienti);
        return "chek/chek-Add";
    }

    @PostMapping("/chek/add")
    public String ChekAdd(@ModelAttribute("chek")@Valid Chek chek,
                             BindingResult bindingResult, Model model,
                             @RequestParam Long user1, @RequestParam Long seansi1,
                             @RequestParam Long suvenirs1, @RequestParam Long klienti1,
                             @RequestParam String colichChek)
    {
        if(bindingResult.hasErrors())
        {
            Iterable<User> user = userRepository.findAll();
            Iterable<Seansi> seansi = seansiRepository.findAll();
            Iterable<Suvenirs> suvenirs = suvenirsRepository.findAll();
            Iterable<Klienti> klienti = klientRepository.findAll();
            model.addAttribute("user", user);
            model.addAttribute("seansi", seansi);
            model.addAttribute("suvenirs", suvenirs);
            model.addAttribute("klienti", klienti);
            System.out.println(bindingResult.getAllErrors());
            return "chek/chek-Add";
        }
        User user = userRepository.findById(user1).orElseThrow();
        chek.setUser(user);
        Seansi seansi = seansiRepository.findById(seansi1).orElseThrow();
        chek.setSeansi(seansi);
        Suvenirs suvenirs = suvenirsRepository.findById(suvenirs1).orElseThrow();
        chek.setSuvenirs(suvenirs);
        Klienti klienti = klientRepository.findById(klienti1).orElseThrow();
        chek.setKlienti(klienti);

        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        int cena = Integer.parseInt(seansi.getPriceSeansa());
        int chelik = Integer.parseInt(colichChek);
        int itog = cena * chelik;
        String Itogo = ""+itog;
        chek.setDataChek(data);
        chek.setTimeChek(time);
        chek.setFinalPriceChek(Itogo);
        chekRepository.save(chek);
        return "redirect:/chek/spisok";
    }

    @GetMapping("/chek/{id}")
    public String ChekDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Chek> chek = chekRepository.findById(id);
        ArrayList<Chek> res = new ArrayList<>();
        chek.ifPresent(res::add);
        model.addAttribute("chek", res);
        if(!chekRepository.existsById(id))
        {
            return "redirect:/chek/spisok";
        }
        return "/chek/chek-Details";
    }

    @PostMapping("/chek/{id}/remove")
    public String ChekDelete(@PathVariable("id")long id, Model model){
        Chek chek = chekRepository.findById(id).orElseThrow();
        chekRepository.delete(chek);
        return "redirect:/chek/spisok";
    }
}
