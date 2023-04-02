package com.drdaza.app.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.drdaza.app.repository.ProfileRepository;
import com.drdaza.app.repository.RoleRepository;
import com.drdaza.app.repository.UserRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository rolesRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void testUserHaveRoles() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("12345");


        Role role = new Role(null, "ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User(null, "user", "user@mail.com", password, roles);
        
        user.setPassword(password);

        rolesRepository.save(role);
        userRepository.save(user);

        User userDB = entityManager.find(User.class, user.getId());

        assertThat("User", userDB.getId(), equalTo(1L));
        assertThat("User role", userDB.getRoles().size(), equalTo(1));
        assertThat("User role name", userDB.getRoles().contains(role));

    }

    @Test
    void test_User_Have_Profile(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("12345");


        Role role = new Role(null, "ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User(null, "user", "user@mail.com", password, roles);
        /* Profile profile = new Profile(null, "have experience"); */

        user.getProfile().setExperience("user have experience");

        user.setPassword(password);

        profileRepository.save(user.getProfile());
        rolesRepository.save(role);
        userRepository.save(user);

        User userDB = entityManager.find(User.class, user.getId());

        assertThat("User", userDB.getId(), equalTo(1L));
        assertThat("User role", userDB.getRoles().size(), equalTo(1));
        assertThat("User role name", userDB.getRoles().contains(role));
        assertThat("user profile",userDB.getProfile().getExperience(), equalTo("user have experience"));
    }
}
