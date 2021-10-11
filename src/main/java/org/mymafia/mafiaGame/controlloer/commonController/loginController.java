package org.mymafia.mafiaGame.controlloer.commonController;

import org.mymafia.mafiaGame.service.kakaoService.KakaoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {

    @Autowired
    private KakaoAPI kakaoAPI;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam("code") String code){
        String access_token = kakaoAPI.getAccessToken(code);
        System.out.println("controller access_token : " + access_token);
        return "index";
    }


}

