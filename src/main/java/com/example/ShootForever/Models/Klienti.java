package com.example.ShootForever.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
public class Klienti
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String familiaKlienta, nameKlienta;

    @Size(min = 0, max = 50, message = "До 50 символов")
    private  String otchKlienta;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 10, max = 10, message = "Введите серию и номер без пробелов")
    private  String pasportKlienta;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 11, max = 11, message = "Введите корректный номер телефона. Пример: 89850346435")
    private  String telephonKlienta;

    @NotNull(message = "Поле не может быть пустым")
    @PastOrPresent(message = "Вы ещё не родились")
    private Date birthdayKlienta;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String mailKlienta;

    public Klienti(String familiaKlienta, String nameKlienta, String otchKlienta, String pasportKlienta, String telephonKlienta, Date birthdayKlienta, String mailKlienta) {
        this.familiaKlienta = familiaKlienta;
        this.nameKlienta = nameKlienta;
        this.otchKlienta = otchKlienta;
        this.pasportKlienta = pasportKlienta;
        this.telephonKlienta = telephonKlienta;
        this.birthdayKlienta = birthdayKlienta;
        this.mailKlienta = mailKlienta;
    }

    public Klienti() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamiliaKlienta() {
        return familiaKlienta;
    }

    public void setFamiliaKlienta(String familiaKlienta) {
        this.familiaKlienta = familiaKlienta;
    }

    public String getNameKlienta() {
        return nameKlienta;
    }

    public void setNameKlienta(String nameKlienta) {
        this.nameKlienta = nameKlienta;
    }

    public String getOtchKlienta() {
        return otchKlienta;
    }

    public void setOtchKlienta(String otchKlienta) {
        this.otchKlienta = otchKlienta;
    }

    public String getPasportKlienta() {
        return pasportKlienta;
    }

    public void setPasportKlienta(String pasportKlienta) {
        this.pasportKlienta = pasportKlienta;
    }

    public String getTelephonKlienta() {
        return telephonKlienta;
    }

    public void setTelephonKlienta(String telephonKlienta) {
        this.telephonKlienta = telephonKlienta;
    }

    public Date getBirthdayKlienta() {
        return birthdayKlienta;
    }

    public void setBirthdayKlienta(Date birthdayKlienta) {
        this.birthdayKlienta = birthdayKlienta;
    }

    public String getMailKlienta() {
        return mailKlienta;
    }

    public void setMailKlienta(String mailKlienta) {
        this.mailKlienta = mailKlienta;
    }
}
