package com.example.Authentication_servevr.repo;

import com.example.Authentication_servevr.Entity.UserEnt;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<UserEnt,String> {


    Optional<UserEnt> findByUserMail(String email);
}
