package com.drdaza.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drdaza.app.exceptions.UserNotFounException;
import com.drdaza.app.models.Profile;
import com.drdaza.app.models.User;
import com.drdaza.app.repository.ProfileRepository;
import com.drdaza.app.repository.UserRepository;
import com.drdaza.app.services.intefaces.BasicService;

@Service
public class UserService implements BasicService<User> {

    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getOne(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFounException("the user with id:" + id +" not found"));
    }

    @Override
    public User Save(User entity) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String passwordEncode = encoder.encode(entity.getPassword());

        entity.setPassword(passwordEncode);

        Profile profile = new Profile(null, "without experience");

        entity.setProfile(profile);

        profileRepository.save(profile);

        return userRepository.save(entity);
    }

}
