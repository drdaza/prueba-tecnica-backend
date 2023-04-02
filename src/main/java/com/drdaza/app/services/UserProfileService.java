package com.drdaza.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drdaza.app.exceptions.ProfileNotFounException;
import com.drdaza.app.models.Profile;
import com.drdaza.app.repository.ProfileRepository;
import com.drdaza.app.services.intefaces.AdminService;

@Service
public class UserProfileService implements AdminService<Profile> {

    private ProfileRepository profileRepository;

    public UserProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> listAll() {
        return profileRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Profile profileToDelete = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFounException("this profile don't exist" + id));

        profileRepository.delete(profileToDelete);
    }

    @Override
    public Profile update(Long id, Profile entity) {

        return profileRepository.findById(id)
                .map(profileUpdate -> {
                    profileUpdate.setExperience(entity.getExperience());
                    profileUpdate.setCourses(entity.getCourses());
                    profileUpdate.setPurchases(entity.getPurchases());
                    return profileRepository.save(profileUpdate);
                }).orElseThrow(() -> new ProfileNotFounException("this profile don't exist" + id));

    }

}
