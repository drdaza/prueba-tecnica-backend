package com.drdaza.app.services;

import java.util.List;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drdaza.app.models.User;
import com.drdaza.app.repository.UserRepository;
import com.drdaza.app.services.intefaces.BasicService;

@Service
public class UserService implements BasicService<User> {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAll'");
    }

    @Override
    public User getOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

    @Override
    public User Save(User entity) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String passwordEncode = encoder.encode(entity.getPassword());

        entity.setPassword(passwordEncode);

        return userRepository.save(entity);
    }

}
