package com.smartbook.controller;

import com.smartbook.entity.VerbGroupMark;
import com.smartbook.service.VerbGroupMarkService;
import lombok.AllArgsConstructor;
import org.bouncycastle.its.asn1.IValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {
    private final VerbGroupMarkService service;

    @GetMapping(value = "/home")
    public String home(@RequestParam String login, Model model) {
        System.out.println("login is = " + login);
        model.addAttribute("login", login);
        return "home";
    }

    @GetMapping(value = "/groups")
    public String groups(Model model) {
        List<VerbGroupMark> groups = service.findAllGroup();
        model.addAttribute("groups", groups);
        model.addAttribute("group", new VerbGroupMark());
        return "home_page";
    }



    @PostMapping(value = "createGroup")
    public String createGroup(@ModelAttribute VerbGroupMark verbGroupMark) {
        System.out.println(verbGroupMark.toString());
        service.addGroup(verbGroupMark);
        return "redirect:/groups";
    }

}
