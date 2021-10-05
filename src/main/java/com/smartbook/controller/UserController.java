package com.smartbook.controller;

import com.smartbook.entity.User;
import com.smartbook.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findAllUser());
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<User> userById(@PathVariable("id") Long id) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(userService.findByIdUser(id));
//
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> userById_1(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findById(id));

    }

    @GetMapping(value = "/ex1")
    public List<User> getAll() {
        return userService.findAllUser();
    }


    @GetMapping(value = "/ex2")
    public ModelAndView listAllUser(ModelAndView modelAndView) throws InterruptedException {

        modelAndView.addObject("users", userService.findAllUser());
//            modelAndView.setViewName("/employees.html");
//        System.out.println(userService.findAllUser().toString());
        return modelAndView;
    }


}
