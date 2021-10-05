package com.smartbook.service;

import com.smartbook.entity.User;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAllUser();

    User findByIdUser(Long id);

    Optional<User> findById(Long id);

}
