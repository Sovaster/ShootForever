package com.example.ShootForever.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Seansi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Поле не может быть пустым")
    private Date dataSeansa;

    @NotEmpty(message = "Поле не может быть пустым")
    private String timeSeansa, prodoljSeansa;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 4, message = "От 1 до 4 символов")
    private String colichBulletSeansa;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 6, message = "От 1 до 4 символов")
    private String priceSeansa;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Poligons poligons;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Weapon weapon;

    public Seansi(Date dataSeansa, String timeSeansa, String prodoljSeansa, String colichBulletSeansa, String priceSeansa, Poligons poligons, Weapon weapon) {
        this.dataSeansa = dataSeansa;
        this.timeSeansa = timeSeansa;
        this.prodoljSeansa = prodoljSeansa;
        this.colichBulletSeansa = colichBulletSeansa;
        this.priceSeansa = priceSeansa;
        this.poligons = poligons;
        this.weapon = weapon;
    }

    public Seansi() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataSeansa() {
        return dataSeansa;
    }

    public void setDataSeansa(Date dataSeansa) {
        this.dataSeansa = dataSeansa;
    }

    public String getTimeSeansa() {
        return timeSeansa;
    }

    public void setTimeSeansa(String timeSeansa) {
        this.timeSeansa = timeSeansa;
    }

    public String getProdoljSeansa() {
        return prodoljSeansa;
    }

    public void setProdoljSeansa(String prodoljSeansa) {
        this.prodoljSeansa = prodoljSeansa;
    }

    public String getColichBulletSeansa() {
        return colichBulletSeansa;
    }

    public void setColichBulletSeansa(String colichBulletSeansa) {
        this.colichBulletSeansa = colichBulletSeansa;
    }

    public String getPriceSeansa() {
        return priceSeansa;
    }

    public void setPriceSeansa(String priceSeansa) {
        this.priceSeansa = priceSeansa;
    }

    public Poligons getPoligons() {
        return poligons;
    }

    public void setPoligons(Poligons poligons) {
        this.poligons = poligons;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}