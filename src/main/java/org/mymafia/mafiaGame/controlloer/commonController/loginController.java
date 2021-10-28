package org.mymafia.mafiaGame.controlloer.commonController;

import org.mymafia.mafiaGame.service.kakaoService.KakaoAPI;
import org.mymafia.mafiaGame.service.memberService.MemberService;
import org.mymafia.mafiaGame.vo.dto.MemberDTO;
import org.mymafia.mafiaGame.vo.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class loginController {

    @Autowired
    private KakaoAPI kakaoAPI;

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/login")
    public String loginPage(Member member , HttpSession session){

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(MemberDTO member, HttpSession session){
        String isLogin = memberService.login(member);
        return isLogin;
    }

    @GetMapping("/join")
    public void goToMemberJoin(){
        //커밋 실험용
    }

    @PostMapping(value = "/join", produces ="application/json")
    @ResponseBody
    //ResponseEntity 로 변경
    public ResponseEntity<MemberDTO> memberJoin(MemberDTO memberDto){
        MemberDTO joinMember = memberService.Join(memberDto);
        if(joinMember != null){
            return new ResponseEntity(joinMember,HttpStatus.valueOf(200));
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/myPage")
    public void goMyPage(){
    }

    @PostMapping("/myPage")
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

