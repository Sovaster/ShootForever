package com.example.ShootForever.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Postav
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 100, message = "От 1 до 100 символов")
    private  String nazvaniePostav;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private GorodPost gorodPost;

    @OneToMany(mappedBy = "postav", cascade = CascadeType.ALL)
    private Collection<Weapon> weapon;

    public Postav(String nazvaniePostav, GorodPost gorodPost, Collection<Weapon> weapons) {
        this.nazvaniePostav = nazvaniePostav;
        this.gorodPost = gorodPost;
        this.weapon = weapons;
    }

    public Postav() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazvaniePostav() {
        return nazvaniePostav;
    }

    public void setNazvaniePostav(String nazvaniePostav) {
        this.nazvaniePostav = nazvaniePostav;
    }

    public GorodPost getGorodPost() {
        return gorodPost;
    }

    public void setGorodPost(GorodPost gorodPost) {
        this.gorodPost = gorodPost;
    }

    public Collection<Weapon> getWeapon() {
        return weapon;
    }

    public void setWeapon(Collection<Weapon> weapon) {
        this.weapon = weapon;
    }
}
