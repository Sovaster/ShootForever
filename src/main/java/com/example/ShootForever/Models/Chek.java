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

}