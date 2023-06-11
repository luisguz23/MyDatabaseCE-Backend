package com.example.mydatabasece_backend.Repo;

import com.example.mydatabasece_backend.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
