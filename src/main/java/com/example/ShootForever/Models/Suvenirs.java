package com.example.ShootForever.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Suvenirs
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String nazvanieSuvenira;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 300, message = "От 1 до 300 символов")
    private  String opisanieSuvenira;

    @OneToMany(mappedBy = "suvenirs", cascade = CascadeType.ALL)
    private Collection<Chek> chek;

    public Suvenirs(String nazvanieSuvenira, String opisanieSuvenira, Collection<Chek> chek) {
        this.nazvanieSuvenira = nazvanieSuvenira;
        this.opisanieSuvenira = opisanieSuvenira;
        this.chek = chek;
    }

    public Suvenirs() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazvanieSuvenira() {
        return nazvanieSuvenira;
    }

    public void setNazvanieSuvenira(String nazvanieSuvenira) {
        this.nazvanieSuvenira = nazvanieSuvenira;
    }

    public String getOpisanieSuvenira() {
        return opisanieSuvenira;
    }

    public void setOpisanieSuvenira(String opisanieSuvenira) {
        this.opisanieSuvenira = opisanieSuvenira;
    }

    public Collection<Chek> getChek() {
        return chek;
    }

    public void setChek(Collection<Chek> chek) {
        this.chek = chek;
    }
}
