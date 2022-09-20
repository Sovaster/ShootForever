package com.example.ShootForever.Repo;

import com.example.ShootForever.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
