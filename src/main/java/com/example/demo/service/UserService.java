package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Not found!");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void saveUser(User user) {
        user.setRoles(roleRepo.findRolesByRoleIn(user.getUserRolesTransient()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public User findUserById(Long id) {
        return userRepo.getById(id);
    }


    public void userEdit(User user) {
        user.setRoles(roleRepo.findRolesByRoleIn(user.getUserRolesTransient()));
        user.setPassword(passwordEncoder.encode(user.getPasswordNew()));
        userRepo.save(user);
    }

    public void userDelete(User user) {
        userRepo.delete(user);
    }
}
