package com.example.ShootForever.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class GorodPost
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String nazvanieGorodaPost;

    @OneToMany(mappedBy = "gorodPost", cascade = CascadeType.ALL)
    private Collection<Postav> postavs;

    public GorodPost(String nazvanieGorodaPost, Collection<Postav> postavs) {
        this.nazvanieGorodaPost = nazvanieGorodaPost;
        this.postavs = postavs;
    }

    public GorodPost() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazvanieGorodaPost() {
        return nazvanieGorodaPost;
    }

    public void setNazvanieGorodaPost(String nazvanieGorodaPost) {
        this.nazvanieGorodaPost = nazvanieGorodaPost;
    }

    public Collection<Postav> getPostavs() {
        return postavs;
    }

    public void setPostavs(Collection<Postav> postavs) {
        this.postavs = postavs;
    }
}
