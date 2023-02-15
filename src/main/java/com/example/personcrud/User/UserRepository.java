package com.example.personcrud.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public Long countById(Long id);

    List<User> findUserByFirstname(String keyword);
}
