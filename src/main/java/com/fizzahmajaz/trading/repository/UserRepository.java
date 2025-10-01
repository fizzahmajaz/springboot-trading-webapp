package com.fizzahmajaz.trading.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fizzahmajaz.trading.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);

}
