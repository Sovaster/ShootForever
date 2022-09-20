package com.example.ShootForever.Repo;

import com.example.ShootForever.Models.Klienti;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KlientRepository extends CrudRepository<Klienti,Long>
{
    List<Klienti> findByFamiliaKlientaContains(String familiaKlienta);
}
