package com.example.ShootForever.Repo;

import com.example.ShootForever.Models.GorodPost;
import com.example.ShootForever.Models.Suvenirs;
import org.springframework.data.repository.CrudRepository;

public interface GorodPostRepository extends CrudRepository<GorodPost,Long>
{
    GorodPost findByNazvanieGorodaPost(String gorod);
}
