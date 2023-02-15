package com.example.personcrud.User;

import java.util.List;

public interface UserServiceImpl {
    List<User> findAll();

    void save(User user);

    public User get(Long id) throws UserNotFoundException;

    List<User> findAllByName(String keyword);

}
