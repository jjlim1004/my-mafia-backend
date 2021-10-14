package org.mymafia.mafiaGame.controlloer.commonController;

import org.mymafia.mafiaGame.service.kakaoService.KakaoAPI;
import org.mymafia.mafiaGame.vo.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class loginController {

    @Autowired
    private KakaoAPI kakaoAPI;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Member member , HttpSession session){

        return "index";
    }

    @GetMapping("/join")
    public void goToMemberJoin(){
    }

    @PostMapping("/join")
    public String memberJoin(){
        return "lobbyPage";
    }

    @GetMapping("/kakao")
    public void getKakaoinfo(@RequestParam("code") String code , HttpSession session){
        String access_token = kakaoAPI.getAccessToken(code);

        System.out.println("controller access_token : " + access_token);

        HashMap<String,Object> userInfo = kakaoAPI.getUserInfo(access_token);

        for(Map.Entry<String,Object> entry : userInfo.entrySet()){
            System.out.println("!!! " + entry.getKey() + "  : " + entry.getValue());
        }

        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_token);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        return "index";
    }
}

