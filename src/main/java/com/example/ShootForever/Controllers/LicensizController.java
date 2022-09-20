package com.example.ShootForever.Controllers;

import com.example.ShootForever.Models.AdresPoligon;
import com.example.ShootForever.Models.Licenzia;
import com.example.ShootForever.Repo.AdresPoligonRepository;
import com.example.ShootForever.Repo.LicenziaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LicensizController
{
    @Autowired
    private LicenziaRepository licenziaRepository;

    @GetMapping("/licenzia/spisok")
    public String Licenzias(Model model)
    {
        Iterable<Licenzia> licenzias = licenziaRepository.findAll();
        model.addAttribute("licenzias",licenzias);
        return "weapon/licenzia-Spisok";
    }

    @GetMapping("/licenzia/add")
    public String LicenziasAdd(Licenzia licenzia, Model model)
    {
        return "weapon/licenzia-Add";
    }

    @PostMapping("/licenzia/add")
    public String LicenziasAdd(@ModelAttribute("licenzia")@Valid Licenzia licenzia, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "weapon/licenzia-Add";
        }
        licenziaRepository.save(licenzia);
        return "redirect:/licenzia/spisok";
    }

    @PostMapping("/licenzia/{id}/remove")
    public String LicenziasDelete(@PathVariable("id")long id, Model model){
        Licenzia licenzia = licenziaRepository.findById(id).orElseThrow();
        licenziaRepository.delete(licenzia);
        return "redirect:/licenzia/spisok";
    }
}
