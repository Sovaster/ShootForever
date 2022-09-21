package com.example.ShootForever.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
public class Chek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataChek;

    private String timeChek;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 2, message = "От 1 до 2 символов")
    private String colichChek;

    private String finalPriceChek;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="seansChek_id")
    private Seansi seansi;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Klienti klienti;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Suvenirs suvenirs;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private User user;

    public Chek(String dataChek, String timeChek, String colichChek, String finalPriceChek, Seansi seansi, Klienti klienti, Suvenirs suvenirs, User user) {
        this.dataChek = dataChek;
        this.timeChek = timeChek;
        this.colichChek = colichChek;
        this.finalPriceChek = finalPriceChek;
        this.seansi = seansi;
        this.klienti = klienti;
        this.suvenirs = suvenirs;
        this.user = user;
    }

    public Chek() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataChek() {
        return dataChek;
    }

    public void setDataChek(String dataChek) {
        this.dataChek = dataChek;
    }

    public String getTimeChek() {
        return timeChek;
    }

    public void setTimeChek(String timeChek) {
        this.timeChek = timeChek;
    }

    public String getColichChek() {
        return colichChek;
    }

    public void setColichChek(String colichChek) {
        this.colichChek = colichChek;
    }

    public String getFinalPriceChek() {
        return finalPriceChek;
    }

    public void setFinalPriceChek(String finalPriceChek) {
        this.finalPriceChek = finalPriceChek;
    }

    public Seansi getSeansi() {
        return seansi;
    }

    public void setSeansi(Seansi seansi) {
        this.seansi = seansi;
    }

    public Klienti getKlienti() {
        return klienti;
    }

    public void setKlienti(Klienti klienti) {
        this.klienti = klienti;
    }

    public Suvenirs getSuvenirs() {
        return suvenirs;
    }

    public void setSuvenirs(Suvenirs suvenirs) {
        this.suvenirs = suvenirs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}