package org.mymafia.mafiaGame.controlloer.commonController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "index";
    }

    @PostMapping("/login")
    public String login(){
        return "mainPage";
    }


}

