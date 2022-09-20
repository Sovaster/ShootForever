package com.example.ShootForever.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
public class AdresPoligon
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String gorodPoligona, streetPoligona;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 3, message = "От 1 до 3 символов")
    private  String domPoligona, korpusPoligona;

    @OneToOne(optional = true, mappedBy = "adresPoligon", cascade = CascadeType.ALL)
    private Poligons poligons;

    public AdresPoligon(String gorodPoligona, String streetPoligona, String domPoligona, String korpusPoligona, Poligons poligons) {
        this.gorodPoligona = gorodPoligona;
        this.streetPoligona = streetPoligona;
        this.domPoligona = domPoligona;
        this.korpusPoligona = korpusPoligona;
        this.poligons = poligons;
    }

    public AdresPoligon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGorodPoligona() {
        return gorodPoligona;
    }

    public void setGorodPoligona(String gorodPoligona) {
        this.gorodPoligona = gorodPoligona;
    }

    public String getStreetPoligona() {
        return streetPoligona;
    }

    public void setStreetPoligona(String streetPoligona) {
        this.streetPoligona = streetPoligona;
    }

    public String getDomPoligona() {
        return domPoligona;
    }

    public void setDomPoligona(String domPoligona) {
        this.domPoligona = domPoligona;
    }

    public String getKorpusPoligona() {
        return korpusPoligona;
    }

    public void setKorpusPoligona(String korpusPoligona) {
        this.korpusPoligona = korpusPoligona;
    }

    public Poligons getPoligons() {
        return poligons;
    }

    public void setPoligons(Poligons poligons) {
        this.poligons = poligons;
    }
}
