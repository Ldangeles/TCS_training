package com.Coffee.CoffeeNetwork.repositories;

import com.Coffee.CoffeeNetwork.models.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u from User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

}
