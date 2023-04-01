package com.drdaza.app.services.securityService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.drdaza.app.models.Role;
import com.drdaza.app.models.User;
import com.drdaza.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class JpaUserDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    JpaUserDetailsService jpaUserDetailsService;

    @BeforeEach
    void setup(){

    }
    @Test
    void test_loadUserByUsername(){
        String username = "admin";

        Set<Role> roles = new HashSet<>();
        Role role = new Role(1L, "ADMIN");

        roles.add(role);

        User user = new User(1L, "admin", "admin@mail.com", "12345", roles);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());

        when(userRepository.findByUsername(username)).thenReturn( Optional.of(user));

        UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);

        
        assertThat(userDetails, is(notNullValue()));
        assertThat(userDetails.getUsername(), is(equalTo(username)));
        assertThat(userDetails.getPassword(), is(equalTo("12345")));
        assertThat(userDetails.getAuthorities().size(), is(equalTo(1)));
        assertThat(userDetails.getAuthorities(), contains(authority));
    }
}
