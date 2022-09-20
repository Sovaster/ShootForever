package com.example.ShootForever.Models;

import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Poligons
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 3, message = "От 1 до 3 символов")
    private  String nomerPoligona;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 3, message = "От 1 до 3 символов")
    private  String colichMestPoligona;

    @OneToMany(mappedBy = "poligons", cascade = CascadeType.ALL)
    private Collection<Seansi> seansi;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="adresPoligona_id")
    private AdresPoligon adresPoligon;

    public Poligons(String nomerPoligona, String colichMestPoligona, Collection<Seansi> seansi, AdresPoligon adresPoligon) {
        this.nomerPoligona = nomerPoligona;
        this.colichMestPoligona = colichMestPoligona;
        this.seansi = seansi;
        this.adresPoligon = adresPoligon;
    }

    public Poligons() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomerPoligona() {
        return nomerPoligona;
    }

    public void setNomerPoligona(String nomerPoligona) {
        this.nomerPoligona = nomerPoligona;
    }

    public String getColichMestPoligona() {
        return colichMestPoligona;
    }

    public void setColichMestPoligona(String colichMestPoligona) {
        this.colichMestPoligona = colichMestPoligona;
    }

    public Collection<Seansi> getSeansi() {
        return seansi;
    }

    public void setSeansi(Collection<Seansi> seansi) {
        this.seansi = seansi;
    }

    public AdresPoligon getAdresPoligon() {
        return adresPoligon;
    }

    public void setAdresPoligon(AdresPoligon adresPoligon) {
        this.adresPoligon = adresPoligon;
    }
}
