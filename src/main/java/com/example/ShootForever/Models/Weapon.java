package com.example.ShootForever.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Weapon
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String nazvanieWeapon;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 4, message = "От 1 до 4 символов")
    private  String godProizWeapon;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 100, message = "От 1 до 100 символов")
    private  String tipPatrWeapon;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Postav postav;

    @OneToMany(mappedBy = "weapon", cascade = CascadeType.ALL)
    private Collection<Seansi> seansi;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="licenzia_id")
    private Licenzia licenzia;

    public Weapon(String nazvanieWeapon, String godProizWeapon, String tipPatrWeapon, Postav postav, Collection<Seansi> seansi, Licenzia licenzia) {
        this.nazvanieWeapon = nazvanieWeapon;
        this.godProizWeapon = godProizWeapon;
        this.tipPatrWeapon = tipPatrWeapon;
        this.postav = postav;
        this.seansi = seansi;
        this.licenzia = licenzia;
    }

    public Weapon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazvanieWeapon() {
        return nazvanieWeapon;
    }

    public void setNazvanieWeapon(String nazvanieWeapon) {
        this.nazvanieWeapon = nazvanieWeapon;
    }

    public String getGodProizWeapon() {
        return godProizWeapon;
    }

    public void setGodProizWeapon(String godProizWeapon) {
        this.godProizWeapon = godProizWeapon;
    }

    public String getTipPatrWeapon() {
        return tipPatrWeapon;
    }

    public void setTipPatrWeapon(String tipPatrWeapon) {
        this.tipPatrWeapon = tipPatrWeapon;
    }

    public Postav getPostav() {
        return postav;
    }

    public void setPostav(Postav postav) {
        this.postav = postav;
    }

    public Collection<Seansi> getSeansi() {
        return seansi;
    }

    public void setSeansi(Collection<Seansi> seansi) {
        this.seansi = seansi;
    }

    public Licenzia getLicenzia() {
        return licenzia;
    }

    public void setLicenzia(Licenzia licenzia) {
        this.licenzia = licenzia;
    }
}
