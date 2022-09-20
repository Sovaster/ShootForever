package com.example.ShootForever.Models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String familiaSotrudnika, nameSotrudnika;

    @Size(min = 0, max = 50, message = "До 50 символов")
    private  String otchSotrudnika;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 10, max = 10, message = "Введите серию и номер без пробелов")
    private  String pasportSotrudnika;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 11, max = 11, message = "Введите корректный номер телефона. Пример: 89850346435")
    private  String telephonSotrudnika;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String mailSotrudnika;

    @NotEmpty(message = "Поле не может быть пустым")
    private String birthdaySotrudnika;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 3, max = 50, message = "От 3 до 50 символов")
    private String username;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 8, max = 50, message = "От 8 до 50 символов")
    private String password;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFamiliaSotrudnika() {
        return familiaSotrudnika;
    }

    public void setFamiliaSotrudnika(String familiaSotrudnika) {
        this.familiaSotrudnika = familiaSotrudnika;
    }

    public String getNameSotrudnika() {
        return nameSotrudnika;
    }

    public void setNameSotrudnika(String nameSotrudnika) {
        this.nameSotrudnika = nameSotrudnika;
    }

    public String getOtchSotrudnika() {
        return otchSotrudnika;
    }

    public void setOtchSotrudnika(String otchSotrudnika) {
        this.otchSotrudnika = otchSotrudnika;
    }

    public String getPasportSotrudnika() {
        return pasportSotrudnika;
    }

    public void setPasportSotrudnika(String pasportSotrudnika) {
        this.pasportSotrudnika = pasportSotrudnika;
    }

    public String getTelephonSotrudnika() {
        return telephonSotrudnika;
    }

    public void setTelephonSotrudnika(String telephonSotrudnika) {
        this.telephonSotrudnika = telephonSotrudnika;
    }

    public String getMailSotrudnika() {
        return mailSotrudnika;
    }

    public void setMailSotrudnika(String mailSotrudnika) {
        this.mailSotrudnika = mailSotrudnika;
    }

    public String getBirthdaySotrudnika() {
        return birthdaySotrudnika;
    }

    public void setBirthdaySotrudnika(String birthdaySotrudnika) {
        this.birthdaySotrudnika = birthdaySotrudnika;
    }

    public User(String familiaSotrudnika, String nameSotrudnika, String otchSotrudnika, String pasportSotrudnika, String telephonSotrudnika, String mailSotrudnika, String birthdaySotrudnika, String username, String password, boolean active, Set<Role> roles) {
        this.familiaSotrudnika = familiaSotrudnika;
        this.nameSotrudnika = nameSotrudnika;
        this.otchSotrudnika = otchSotrudnika;
        this.pasportSotrudnika = pasportSotrudnika;
        this.telephonSotrudnika = telephonSotrudnika;
        this.mailSotrudnika = mailSotrudnika;
        this.birthdaySotrudnika = birthdaySotrudnika;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User() {
    }
}
