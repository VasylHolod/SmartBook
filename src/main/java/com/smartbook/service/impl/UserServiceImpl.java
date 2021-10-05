package com.smartbook.service.impl;

import com.smartbook.entity.User;
import com.smartbook.repository.UserRepo;
import com.smartbook.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.glassfish.jersey.internal.guava.Lists;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public List<User> findAllUser() {
        System.out.println(Lists.newArrayList(userRepo.findAll()).toString());
        return Lists.newArrayList(userRepo.findAll());
    }

    @Override
    public User findByIdUser(Long id) {
//        System.out.println(userRepo.getOne(id).toString());
        return userRepo.getOne(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        System.out.println(userRepo.findById(id).toString());
        return userRepo.findById(id);
    }


}
