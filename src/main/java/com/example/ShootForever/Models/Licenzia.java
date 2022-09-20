package com.example.ShootForever.Models;

import org.aspectj.lang.annotation.DeclareError;
import org.hibernate.annotations.Check;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.websocket.OnError;
import java.sql.Date;

@Entity
public class Licenzia
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 20, message = "От 1 до 20 символов")
    private  String nomerLicenzii;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String organLicenzii;

    @NotNull(message = "Поле не может быть пустым")
    private Date dataStartLicenzii, dataFinalLicenzii;

    @OneToOne(optional = true, mappedBy = "licenzia", cascade = CascadeType.ALL)
    private Weapon weapon;

    public Licenzia(String nomerLicenzii, String organLicenzii, Date dataStartLicenzii, Date dataFinalLicenzii, Weapon weapon) {
        this.nomerLicenzii = nomerLicenzii;
        this.organLicenzii = organLicenzii;
        this.dataStartLicenzii = dataStartLicenzii;
        this.dataFinalLicenzii = dataFinalLicenzii;
        this.weapon = weapon;
    }

    public Licenzia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomerLicenzii() {
        return nomerLicenzii;
    }

    public void setNomerLicenzii(String nomerLicenzii) {
        this.nomerLicenzii = nomerLicenzii;
    }

    public String getOrganLicenzii() {
        return organLicenzii;
    }

    public void setOrganLicenzii(String organLicenzii) {
        this.organLicenzii = organLicenzii;
    }

    public Date getDataStartLicenzii() {
        return dataStartLicenzii;
    }

    public void setDataStartLicenzii(Date dataStartLicenzii) {
        this.dataStartLicenzii = dataStartLicenzii;
    }

    public Date getDataFinalLicenzii() {
        return dataFinalLicenzii;
    }

    public void setDataFinalLicenzii(Date dataFinalLicenzii) {
        this.dataFinalLicenzii = dataFinalLicenzii;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
