package com.example.personcrud.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImpl{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User get(Long id) throws UserNotFoundException {
        Optional<User> result= userRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Couldn't find any users by id" + id);
    }

    @Override
    public List<User> findAllByName(String keyword) {
        return userRepository.findUserByFirstname(keyword);
    }

    public void delete(Long id) throws UserNotFoundException{
        Long count  = userRepository.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Couldnt find any User with ID"  + id);
        }
        userRepository.deleteById(id);
    }


}
