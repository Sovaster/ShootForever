package com.example.ShootForever.Repo;

import com.example.ShootForever.Models.GorodPost;
import com.example.ShootForever.Models.Postav;
import org.springframework.data.repository.CrudRepository;

public interface PostavRepository extends CrudRepository<Postav,Long>
{
    Postav findByNazvaniePostav(String postav);
}
